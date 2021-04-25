package org.hswebframework.isdp.IsdpDimensionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hswebframework.web.authorization.DimensionType;

/**
 * 扩展维度信息
 * 实现组织机构的精细化管理
 */
@Getter
@AllArgsConstructor
public enum IsdpOrganDimensionType implements DimensionType {
    district("行政区"),
    organization("机构"),
    company("法人"),
    departmetn("部门"),
    employee("员工"),
    position("岗位");


    @Override
    public String getId() {
        return name();
    }

    private String name;

}
