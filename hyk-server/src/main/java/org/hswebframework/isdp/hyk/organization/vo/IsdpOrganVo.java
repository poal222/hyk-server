package org.hswebframework.isdp.hyk.organization.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.ezorm.rdb.mapping.annotation.Comment;
import org.hswebframework.ezorm.rdb.mapping.annotation.EnumCodec;
import org.hswebframework.ezorm.rdb.mapping.annotation.JsonCodec;
import org.hswebframework.isdp.hyk.organization.entity.OrganExtendEntity;
import org.hswebframework.isdp.hyk.organization.enums.OrganEnum;
import org.hswebframework.web.system.authorization.api.entity.DimensionEntity;
import org.hswebframework.web.validator.CreateGroup;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.JDBCType;
import java.util.List;
import java.util.Map;

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
