package org.hswebframework.isdp.hyk.organization.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.ezorm.rdb.mapping.annotation.EnumCodec;
import org.hswebframework.isdp.hyk.enums.TestEnum;
import org.hswebframework.isdp.hyk.organization.enums.OrganEnum;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 机构类型，区分省直、市直、区县、保荐机构
 */
@Table(name = "s_organ_Type")
@Getter
@Setter
public class OrganTypeEntity extends GenericEntity<String> {

	@Schema(description = "机构id")
	@ColumnType(javaType = String.class)
	private String id;

	//枚举 机构类型
	@Schema(description = "机构类型，区分省直、市直、区县、保荐机构")
	@Column(length = 32)
	@EnumCodec
	@ColumnType(javaType = String.class)
	private OrganEnum organType;

}
