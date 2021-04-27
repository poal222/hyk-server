package org.hswebframework.isdp.sdqysb.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.JDBCType;

@Getter
@Setter
@Table(name = "comp_ipo_info")
public class CompIpoInfo extends GenericEntity<String> {
    /**
     * 企业ID
     */
    @Schema(description = "企业ID")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "comp_id", length = 64)
    private String compId;
    /**
     * 挂牌意向
     */
    @Schema(description = "挂牌意向")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "ipo_forward", length = 64)
    private String ipoForward;
    /**
     * 所处阶段
     */
    @Schema(description = "所处阶段")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "what_step", length = 64)
    private String whatStep;
    /**
     * 预计进入下一阶段时间
     */
    @Schema(description = "预计进入下一阶段时间")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "next_step_time", length = 64)
    private String nextStepTime;
    /**
     * 预计进入辅导备案时间
     */
    @Schema(description = "预计进入辅导备案时间")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "estimate_guidance_time", length = 64)
    private String estimateGuidanceTime;
    /**
     * 中介机构类型ID
     */
    @Schema(description = "中介机构类型ID")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "intermediary_id", length = 64)
    private String intermediaryId;
    /**
     * 中介机构类型
     */
    @Schema(description = "中介机构类型")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "intermediary_type", length = 64)
    private String intermediaryType;
    /**
     * 中介机构名称
     */
    @Schema(description = "中介机构名称")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "is_delete", length = 64)
    private String intermediary_name;
    /**
     * 中介机构联系人
     */
    @Schema(description = "中介机构联系人")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "intermediary_contact_name", length = 64)
    private String intermediaryContactName;
    /**
     * 中介机构联系方式
     */
    @Schema(description = "中介机构联系方式")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "intermediary_contact_phone", length = 64)
    private String intermediaryContactPhone;
    /**
     * 删除标识
     */
    @Schema(description = "删除标识")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "is_delete", length = 64)
    private String isDelete;
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
    private String lastUpdateTime;
    /**
     * 版本
     */
    @Schema(description = "版本")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "version", length = 64)
    private String version;
}
