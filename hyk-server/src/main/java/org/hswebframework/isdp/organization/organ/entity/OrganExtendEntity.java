package org.hswebframework.isdp.organization.organ.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 *
 * 机构扩展表，
 * 涵盖机构类型，租户id等信息
 *
 */
@Table(name = "s_organ_extend")
@Getter
@Setter
public class OrganExtendEntity extends GenericEntity<String> {


	//枚举 机构类型
    @Schema(description = "district(\"行政区\"),\n" +
            "    organization(\"机构\"),\n" +
            "    company(\"法人\"),\n" +
            "    departmetn(\"部门\"),\n" +
            "    employee(\"员工\"),\n" +
            "    position(\"岗位\");")
    @Column(length = 32)
//	@EnumCodec
    @ColumnType(javaType = String.class)
//	private OrganEnum organType;
    private String organType;

	@Schema(description = "短名")
	@Column(length = 64)
	@ColumnType(javaType = String.class)
	private String shortName;

	@Schema(description = "所属行政区划")
	@Column(length = 64)
	@ColumnType(javaType = String.class)
	private String originCode;

	@Schema(description = "租户id")
	@Column(length = 64)
	@ColumnType(javaType = String.class)
	private String tenantId;



}
