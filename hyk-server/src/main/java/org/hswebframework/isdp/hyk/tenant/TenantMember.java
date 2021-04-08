package org.hswebframework.isdp.hyk.tenant;

import lombok.NonNull;
import org.hswebframework.isdp.hyk.tenant.entity.CurrentTenantHolder;
import org.hswebframework.web.authorization.Authentication;

import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

/**
 * 当前租户成员信息,用于获取当前访问系统的租户成员信息.进行判定资产权限,权限控制等.
 *
 * @author 王岗
 * @since 1.2
 */
public interface TenantMember {

    /**
     * @return 租户信息
     */
    @NonNull
    Tenant getTenant();

    /**
     * @return 系统用户ID
     */
    @NonNull
    String getUserId();

    /**
     * @return 是否为管理员
     */
    boolean isAdmin();

    /**
     * @return 是否为主租户
     */
    boolean isMain();




    /**
     * 获取当前租户信息
     *
     * @return 租户信息
     */
    static @NonNull Mono<TenantMember> current() {
        return CurrentTenantHolder.current();
    }

    /**
     * 从认证信息中获取租户信息
     *
     * @param authentication 权限信息
     * @return 租户信息
     */
    static @NotNull Mono<TenantMember> fromAuth(Authentication authentication) {
        return CurrentTenantHolder.fromAuth(authentication);
    }



}
