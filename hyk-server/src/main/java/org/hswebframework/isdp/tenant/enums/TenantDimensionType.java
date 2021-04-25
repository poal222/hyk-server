package org.hswebframework.isdp.tenant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hswebframework.web.dict.EnumDict;
@Getter
@AllArgsConstructor
public enum TenantDimensionType implements EnumDict<String> {

    tenantMember("租户成员"),
    tenantCustomer("租户客户");

    private final String text;

    @Override
    public String getValue() {
        return name();

    }


}

