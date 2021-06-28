package org.hswebframework.isdp.sdqysb.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.JDBCType;

@Table(name = "comp_finance")
@Getter
@Setter
public class CompFinance extends GenericEntity<String> {
    /**
     * 企业ID
     */
    @Schema(description = "企业ID")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "comp_id", length = 64)
    private String compId;
    /**
     * 财务年度
     */
    @Schema(description = "财务年度")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "year", length = 64)
    private String year;
    /**
     * 营业收入
     */
    @Schema(description = "营业收入")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "income", length = 64)
    private String income;
    /**
     * 净利润
     */
    @Schema(description = "净利润")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "net", length = 64)
    private String net;
    /**
     * 研发投入
     */
    @Schema(description = "研发投入")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "investment", length = 64)
    private String investment;
    /**
     * 总资产
     */
    @Schema(description = "总资产")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "totalAssest", length = 64)
    private String totalAssest;
    /**
     * 净资产
     */
    @Schema(description = "净资产")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "netAssest", length = 64)
    private String netAssest;
    /**
     * 删除标识
     */
    @Schema(description = "删除标识")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "isDelete", length = 64)
    private String isDelete;
    /**
     * 创建日期
     */
    @Schema(description = "创建日期")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "createTime", length = 64)
    private String createTime;
    /**
     * 最后一次更新时间
     */
    @Schema(description = "最后一次更新时间")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "lastUpdateTime", length = 64)
    private String lastUpdateTime;
    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "createUserId", length = 64)
    private String createUserId;
    /**
     * 提报版本
     */
    @Schema(description = "提报版本")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "version", length = 64)
    private String version;

}
