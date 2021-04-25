package org.hswebframework.isdp.tenant.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hswebframework.isdp.tenant.Tenant;
import org.hswebframework.isdp.tenant.TenantMember;

@AllArgsConstructor
@Slf4j
public class DefaultTenantMember implements TenantMember {

    @Getter
    private final String userId;

    @Getter
    private final Tenant tenant;


    @Getter
    private final boolean admin;

    @Getter
    private final boolean main;


}
