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
 * 知识产权
 * 发明
 */
@Table(name = "comp_standard")
@Getter
@Setter
public class CompStandard extends GenericEntity<String> {
    /**
     * 企业ID
     */
    @Schema(description = "企业ID")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "comp_id", length = 64)
    private String compId;

    /**
     * 标准类型
     */
    @Schema(description = "标准类型")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "standard_type", length = 64)
    private String standardType;
    /**
     * 标准名称
     */
    @Schema(description = "标准名称")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "standard_name", length = 64)
    private String standardName;
    /**
     * 版本号
     */
    @Schema(description = "标准号")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "standard_No", length = 64)
    private String standardNo;
    /**
     * 参与制定的标准版本
     */
    @Schema(description = "参与制定的标准版本")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "standard_version", length = 64)
    private String standardVersionn;
    /**
     * 发布单位
     */
    @Schema(description = "发布单位")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "standard_co", length = 64)
    private String standardCo;
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
