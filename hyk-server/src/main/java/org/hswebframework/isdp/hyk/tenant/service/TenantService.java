package org.hswebframework.isdp.hyk.tenant.service;

import org.hswebframework.isdp.hyk.tenant.entity.TenantEntity;
import org.hswebframework.isdp.hyk.tenant.entity.TenantMemberEntity;
import org.hswebframework.isdp.hyk.tenant.vo.TenantDetail;
import org.hswebframework.web.api.crud.entity.PagerResult;
import org.hswebframework.web.api.crud.entity.QueryParamEntity;
import org.hswebframework.web.crud.service.GenericReactiveCacheSupportCrudService;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.system.authorization.api.service.reactive.ReactiveUserService;
import org.hswebframework.web.validator.ValidatorUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 租户管理 服务类
 * @author 王岗
 */
@Service
public class TenantService extends GenericReactiveCacheSupportCrudService<TenantEntity, String> {

    private final TenantMemberService memberService;

    public TenantService(TenantMemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public String getCacheName() {
        return "tenants";
    }

    @Transactional
    public Mono<TenantEntity> createTenant(CreateTenantRequest request) {
        ValidatorUtils.tryValidate(request);

        String id = IDGenerator.MD5.generate();
        TenantEntity entity = request.toTenantEntity();
        entity.setId(id);
        return this
            .insert(Mono.just(entity))
            .flatMap(ignore ->
                memberService
                    .createMember(id, CreateMemberRequest.builder()
                        .admin(true)
                        .name(request.getName())
                        .username(request.getUsername())
                        .password(request.getPassword())
                        .description(request.getDescription())
                        .build()))
            .then(findById(id));

    }

    public Mono<PagerResult<TenantDetail>> queryTenantDetail(QueryParamEntity entity) {
        return this
            .queryPager(entity)
            .flatMap(result ->
                Flux.fromIterable(result.getData())
                    .map(TenantDetail::of)
                    .flatMap(detail -> memberService.createQuery()
                        .where(TenantMemberEntity::getTenantId, detail.getTenant().getId())
                        .count()
                        .doOnNext(detail::setMembers)
                        .thenReturn(detail))
                    .collectList()
                    .map(list -> PagerResult.of(result.getTotal(), list, entity)));
    }
}
