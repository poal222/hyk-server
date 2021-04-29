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

@Table(name = "comp_business_info")
@Getter
@Setter
public class CompBusinessInfo extends GenericEntity<String> {
    /**
     * 企业ID
     */
    @Schema(description = "企业ID")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "comp_id", length = 64)
    /** 企业ID */
    private String compId;
    /**
     * 主营业务名称
     */
    @Schema(description = "主营业务名称")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "major_bussiness", length = 64)
    private String majorBussiness;
    /**
     * 拥有自主产品情况
     */
    @Schema(description = "拥有自主产品情况")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "own_status", length = 64)
    private String ownStatus;
    /**
     * 收入占比
     */
    @Schema(description = "收入占比")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "income_percent", length = 64)
    private String incomePercent;
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
     * 版本;和主表版本需一致
     */
    @Schema(description = " 版本;和主表版本需一致")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "version", length = 64)
    private String version;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "create_time", length = 64)
    @DefaultValue(generator = Generators.CURRENT_TIME)
    private String createTime;
    /**
     * 最后一次更新时间
     */
    @Schema(description = "最后一次更新时间")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "last_update_time", length = 64)
    @DefaultValue(generator = Generators.CURRENT_TIME)
    private String lastUpdateTime;
}
