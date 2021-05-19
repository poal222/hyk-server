package org.hswebframework.isdp.Verfiy.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hswebframework.web.dict.EnumDict;

@Getter
@AllArgsConstructor
public enum VerfiyEmnu implements EnumDict<String> {

    grrz("个人认证"),
    qyrz("企业认证"),
    jgrz("机构认证"),
    zzrz("组织认证");
    private final String text;

    @Override
    public String getValue() {
        return name();
    }
}
