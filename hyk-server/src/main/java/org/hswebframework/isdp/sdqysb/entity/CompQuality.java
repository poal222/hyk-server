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

/**
 * 专业资质
 */
@Table(name = "comp_Quality")
@Getter
@Setter
public class CompQuality extends GenericEntity<String> {
    /**
     * 企业ID
     */
    @Schema(description = "企业ID")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "comp_id", length = 64)
    private String compId;


    /**
     * 资质名称
     */
    @Schema(description = "资质名称")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "quality_name", length = 64)
    private String qualityName;


    /**
     * 取的时间
     */
    @Schema(description = "取的时间")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "get_time", length = 64)
    private String getTime;
    /**
     * 有效期
     */
    @Schema(description = "有效期")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "period", length = 64)
    private String period;
    /**
     * 认证单位
     */
    @Schema(description = "认证单位")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "quality_co", length = 64)
    private String qualityCo;
    /**
     * 删除标识
     */
    @Schema(description = "删除标识")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "is_delete", length = 10)
    private String isDelete;
    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "create_user_id", length = 10)
    private String createUserId;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "create_time", length = 10)
    @DefaultValue(generator = Generators.CURRENT_TIME)
    private String createTime;
    /**
     * 最后一次更新时间
     */
    @Schema(description = "最后一次更新时间")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "last_update_time", length = 10)
    @DefaultValue(generator = Generators.CURRENT_TIME)
    private String lastUpdateTime;
    /**
     * 提交版本;每次审核通过后，作为一个新版本
     */
    @Schema(description = "提交版本")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "version", length = 10)
    private String version;
}
