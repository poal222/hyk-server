package org.hswebframework.isdp.organization.vo;

import lombok.Getter;
import lombok.Setter;
import org.hswebframework.isdp.organization.organ.entity.OrganExtendEntity;
import org.hswebframework.web.system.authorization.api.entity.DimensionEntity;

import java.io.Serializable;

@Getter
@Setter
public class IsdpOrganVo implements Serializable {

    private DimensionEntity dimensionEntity;

    private OrganExtendEntity organExtendEntity;
//    @Comment("维度类型ID")
//    @Column(length = 32, name = "type_id")
//    @Schema(description = "维度类型ID")
//    private String typeId;
//
//    @Comment("维度名称")
//    @Column(length = 32)
//    @Schema(description = "维度名称")
//    @NotBlank(message = "名称不能为空", groups = CreateGroup.class)
//    private String name;
//
//    @Comment("描述")
//    @Column(length = 256)
//    @Schema(description = "说明")
//    private String describe;
//
//    @Column
//    @ColumnType(jdbcType = JDBCType.LONGVARCHAR)
//    @Comment("其他配置")
//    @JsonCodec
//    @Schema(description = "其他配置")
//    private Map<String, Object> properties;
//
//    @Schema(description = "子节点")
//    private List<DimensionEntity> children;
//    @Schema(description = "机构id")
//    @ColumnType(javaType = String.class)
//    private String id;
//    /** 机构拓展信息 **/
//    //枚举 机构类型
//    @Schema(description = "district(\"行政区\"),\n" +
//            "    organization(\"机构\"),\n" +
//            "    company(\"法人\"),\n" +
//            "    departmetn(\"部门\"),\n" +
//            "    employee(\"员工\"),\n" +
//            "    position(\"岗位\");")
//    @Column(length = 32)
//    @EnumCodec
//    @ColumnType(javaType = String.class)
//    private OrganEnum organType;
//    //枚举 机构类型
//    @Schema(description = "租户id")
//    @Column(length = 64)
//    @ColumnType(javaType = String.class)
//    private String tenantId;
}
