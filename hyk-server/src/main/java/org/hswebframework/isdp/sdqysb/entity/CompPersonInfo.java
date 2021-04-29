package org.hswebframework.isdp.sdqysb.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.ezorm.rdb.mapping.annotation.DefaultValue;
import org.hswebframework.web.api.crud.entity.GenericEntity;
import org.hswebframework.web.crud.generator.Generators;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.JDBCType;

@Getter
@Setter
@Table(name = "comp_person_info")
public class CompPersonInfo extends GenericEntity<String> {
    /**
     * 企业ID
     */
    @Schema(description = "企业ID")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "comp_id", length = 64)
    private String compId;
    /**
     * 人员类别ID
     */
    @Schema(description = "人员类别ID")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "type_id", length = 64)
    private String typeId;
    /**
     * 人员类别名称
     */
    @Schema(description = "人员类别名称")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "type_name", length = 64)
    private String typeName;
    /**
     * 姓名
     */
    @Schema(description = "姓名")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "name", length = 64)
    private String name;
    /**
     * 手机号
     */
    @Schema(description = "手机号")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "phone", length = 64)
    private String phone;
    /**
     * 删除标识
     */
    @Schema(description = "删除标识")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "is_delete", length = 64)
    private String isDelete;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "create_time", length = 64)
    @DefaultValue(generator = Generators.CURRENT_TIME)
    private String createTime;
    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "create_user_id", length = 64)
    private String createUserId;
    /**
     * 最后一次更新时间
     */
    @Schema(description = "最后一次更新时间")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "last_update_time", length = 64)
    @DefaultValue(generator = Generators.CURRENT_TIME)
    private String lastUpdateTime;
    /**
     * 版本
     */
    @Schema(description = "版本")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "version", length = 64)
    private String version;
}