package org.hswebframework.isdp.hyk.tenant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hswebframework.web.dict.EnumDict;

@Getter
@AllArgsConstructor
public enum TenantMemberState implements EnumDict<String> {

    enabled("正常"),
    disabled("已禁用");

    private final String text;

    @Override
    public String getValue() {
        return name();
    }

}
