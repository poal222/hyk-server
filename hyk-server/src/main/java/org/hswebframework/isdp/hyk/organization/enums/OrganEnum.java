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

    ProviceOrgan("省直机构"),
    CityOragn("市直机构"),
    CountyOrgan("区县机构"),
	FinancialOrgan("金融机构"),
	;
    private final String text;
    @Override
    public String getValue() {
        return name();
    }
}
