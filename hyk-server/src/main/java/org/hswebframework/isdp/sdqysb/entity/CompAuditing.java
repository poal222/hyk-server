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

@Table(name = "comp_auditing")
@Getter
@Setter
public class CompAuditing extends GenericEntity<String> {
    /**
     * 企业用户ID
     */
    @Schema(description = "企业ID")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "comp_id", length = 64)
    private String compId;
    /**
     * 第一级审核机构
     */
    @Schema(description = "第一级审核机构")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "first_auditing", length = 64)
    private String firstAuditing;
    /**
     * 第二级审核机构
     */
    @Schema(description = "第二级审核机构")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "second_auditing", length = 64)
    private String secondAuditing;
    /**
     * 第三级审核机构
     */
    @Schema(description = "第三级审核机构")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "third_auditing", length = 64)
    private String thirdAuditing;
    /**
     * 第一级审核机构审核结果
     */
    @Schema(description = "第一级审核机构")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "first_auditing_result", length = 64)
    private String firstAuditingResult;
    /**
     * 第二级审核机构审核结果
     */
    @Schema(description = "第二级审核机构审核结果")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "second_auditing_result", length = 64)
    private String secondAuditingResult;
    /**
     * 第三级审核机构审核结果
     */
    @Schema(description = "第三级审核机构审核结果")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "third_auditing_result", length = 64)
    private String thirdAuditingResult;
    /**
     * 第一级审核机构反馈意见
     */
    @Schema(description = "第一级审核机构反馈意见")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "first_auditing_view", length = 255)
    private String firstAuditingView;
    /**
     * 第二级审核机构反馈意见
     */
    @Schema(description = "第二级审核机构反馈意见")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "second_auditing_view", length = 255)
    private String secondAuditingView;
    /**
     * 第三级审核机构反馈意见
     */
    @Schema(description = "第三级审核机构反馈意见")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "third_auditing_view", length = 255)
    private String thirdAuditingView;
    /**
     * 本轮审核机构
     */
    @Schema(description = "本轮审核机构")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "this_auditing", length = 64)
    private String thisAuditing;
    /**
     * 审核版本
     */
    @Schema(description = "审核版本")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "version", length = 64)
    private String version;
    /**
     * 最后一次更新时间
     */
    @Schema(description = "最后一次更新时间")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "last_update_time", length = 64)
    @DefaultValue(generator = Generators.CURRENT_TIME)
    private String lastUpdateTime;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "create_time", length = 64)
    @DefaultValue(generator = Generators.CURRENT_TIME)
    private String createTime;
}
