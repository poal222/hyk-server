package org.hswebframework.isdp.hyk.tenant.service;

import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.hyk.tenant.entity.TenantEntity;
import org.hswebframework.isdp.hyk.tenant.entity.TenantMemberDetail;
import org.hswebframework.isdp.hyk.tenant.entity.TenantMemberEntity;
import org.hswebframework.isdp.hyk.tenant.enums.TenantDimensionType;
import org.hswebframework.web.crud.service.GenericReactiveCrudService;
import org.hswebframework.web.system.authorization.api.entity.UserEntity;
import org.hswebframework.web.system.authorization.api.event.ClearUserAuthorizationCacheEvent;
import org.hswebframework.web.system.authorization.api.service.reactive.ReactiveUserService;
import org.hswebframework.web.validator.ValidatorUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TenantMemberService extends GenericReactiveCrudService<TenantMemberEntity, String> {


    private final ReactiveRepository<TenantEntity, String> tenantRepository;

    private final ReactiveUserService userService;

    private final ApplicationEventPublisher eventPublisher;

    public TenantMemberService(
            ReactiveRepository<TenantEntity, String> tenantRepository,
            ReactiveUserService userService, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
        this.tenantRepository = tenantRepository;
    }

    @Transactional
    public Mono<Void> bindMembers(String tenantId, Flux<BindMemberRequest> bind) {

        return bind
                .map(request -> {
                    ValidatorUtils.tryValidate(request);
                    TenantMemberEntity entity = request.toMember();
                    entity.setTenantId(tenantId);
                    entity.setMainTenant(true);
                    entity.generateId();
                    return entity;
                })
                .doOnNext(mem -> eventPublisher.publishEvent(ClearUserAuthorizationCacheEvent.of(mem.getUserId())))
                .as(this::save)
                .then();
    }

    @Transactional
    public Mono<Void> unbindMembers(String tenantId, Flux<String> bindIdStream) {

        Flux<String> cache = bindIdStream.cache();

        return findById(cache)
                .map(TenantMemberEntity::getUserId)
                .collectList()
                .doOnNext(list -> eventPublisher.publishEvent(ClearUserAuthorizationCacheEvent.of(list)))
                .then(deleteById(cache))
                .then();
    }

    @Transactional
    public Mono<TenantMemberEntity> createMember(String tenantId, CreateMemberRequest request) {
        return userService
                .findByUsername(request.getUsername())
                .map(u -> new IllegalArgumentException("用户[" + u.getUsername() + "]已存在"))
                .then(Mono.defer(() -> {
                    UserEntity user = new UserEntity();
                    user.setName(request.getName());
                    user.setUsername(request.getUsername());
                    user.setCreateTime(System.currentTimeMillis());
                    user.setPassword(request.getPassword());
                    user.setStatus((byte) 1);
                    user.setType(String.valueOf(TenantDimensionType.tenantMember));
                    return userService
                            .saveUser(Mono.just(user))
                            .flatMap(ignore -> {
                                TenantMemberEntity entity = request.toMember();
                                entity.setUserId(user.getId());
                                entity.setTenantId(tenantId);
                                entity.generateId();
                                return save(Mono.just(entity))
                                        .thenReturn(entity);
                            });
                }));
    }


    public Flux<TenantMemberEntity> findByUserId(String userId) {
        Assert.hasText(userId, "userId can not be null");
        return createQuery()
                .where(TenantMemberEntity::getUserId, userId)
                .fetch();
    }

    public Flux<TenantMemberEntity> findByTenantId(String tenantId) {
        Assert.hasText(tenantId, "tenantId can not be null");
        return createQuery()
                .where(TenantMemberEntity::getTenantId, tenantId)
                .fetch();

    }

    public Flux<TenantMemberDetail> findMemberDetail(String userId) {
        Assert.hasText(userId, "userId can not be null");
        return findByUserId(userId)
                .collectList()
                .flatMapMany(members -> {
                    Set<String> tenantId = members.stream()
                            .map(TenantMemberEntity::getTenantId)
                            .collect(Collectors.toSet());
                    return tenantRepository
                            .createQuery()
                            .where().in(TenantEntity::getId, tenantId)
                            .fetch()
                            .collectMap(TenantEntity::getId)
                            .flatMapMany(tenants -> Flux
                                    .fromIterable(members)
                                    .map(member -> TenantMemberDetail.of(member, tenants.get(member.getTenantId()))));
                });

    }

}
