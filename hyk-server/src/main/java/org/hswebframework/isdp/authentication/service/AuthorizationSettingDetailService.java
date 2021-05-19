package org.hswebframework.isdp.authentication.service;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.hswebframework.isdp.authentication.request.AuthorizationSettingDetail;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.DimensionProvider;
import org.hswebframework.web.system.authorization.api.entity.AuthorizationSettingEntity;
import org.hswebframework.web.system.authorization.defaults.configuration.PermissionProperties;
import org.hswebframework.web.system.authorization.defaults.service.DefaultAuthorizationSettingService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 授权设置管理,用于保存授权配置以及获取授权设置详情.
 *
 * @see DefaultAuthorizationSettingService,DimensionProvider
 * @since 1.0
 */
@Component
@AllArgsConstructor
public class AuthorizationSettingDetailService {

    private final DefaultAuthorizationSettingService settingService;
    private final List<DimensionProvider> providers;
    private final PermissionProperties permissionProperties;

    /**
     * 保存授权设置详情
     *
     * @param detailFlux
     * @return
     */
    @Transactional
    public Mono<Void> saveDetail(Authentication authentication, Flux<AuthorizationSettingDetail> detailFlux) {
        return detailFlux
                //先删除旧的权限设置
                .flatMap(detail -> settingService.getRepository().createDelete()
                        .where(AuthorizationSettingEntity::getDimensionType, detail.getTargetType())
                        .and(AuthorizationSettingEntity::getDimensionTarget, detail.getTargetId())
                        .execute()
                        .thenReturn(detail))
                .flatMap(detail ->
                        Flux.fromIterable(providers)
                                .flatMap(provider -> provider
                                        .getAllType()
                                        .filter(type -> type.getId().equals(detail.getTargetType()))//过滤掉不同的维度类型
                                        .singleOrEmpty()
                                        .flatMap(type -> provider.getDimensionById(type, detail.getTargetId()))
                                        .flatMapIterable(detail::toEntity))
                                .switchIfEmpty(Flux.defer(() -> Flux.fromIterable(detail.toEntity())))
                                .distinct(AuthorizationSettingEntity::getPermission)
                )
                .map(entity -> permissionProperties.getFilter().handleSetting(authentication, entity))
                .filter(e -> CollectionUtils.isNotEmpty(e.getActions()))
                .as(settingService::save)
                .then();
    }

    //获取权限详情
    public Mono<AuthorizationSettingDetail> getSettingDetail(String targetType,
                                                             String target) {
        return settingService
                .createQuery()
                .where(AuthorizationSettingEntity::getDimensionTarget, target)
                .and(AuthorizationSettingEntity::getDimensionType, targetType)
                .fetch()
                .collectList()
                .map(AuthorizationSettingDetail::fromEntity)
                ;
    }

}
