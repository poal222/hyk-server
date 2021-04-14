package org.hswebframework.isdp.hyk.organization.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hswebframework.web.dict.EnumDict;

@Getter
@AllArgsConstructor
@JsonDeserialize(
        contentUsing = EnumDict.EnumDictJSONDeserializer.class
)
public enum OrganEnum implements EnumDict<String> {

    district("行政区"),
    organization("机构"),
    company("法人"),
    departmetn("部门"),
    employee("员工"),
    position("岗位");
	;
    private final String text;
    @Override
    public String getValue() {
        return name();
    }
}
