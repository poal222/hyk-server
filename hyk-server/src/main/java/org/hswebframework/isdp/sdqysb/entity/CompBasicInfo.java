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

@Table(name = "comp_basic_info")
@Getter
@Setter
public class CompBasicInfo extends GenericEntity<String> {

    /**
     * 企业ID
     */
    @Schema(description = "企业ID")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "comp_id", length = 64)
    private String compId;
    /**
     * 企业名称
     */
    @Schema(description = "企业名称")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "comp_name", length = 64)
    private String compName;
    /**
     * 统一社会信用代码
     */
    @Schema(description = "统一社会信用代码")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "uscc_id", length = 64)
    private String usccId;
    /**
     * 成立时间
     */
    @Schema(description = "成立时间")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "reg_time", length = 64)
    private String regTime;
    /**
     * 注册资本
     */
    @Schema(description = "注册资本")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "reg_capital", length = 64)
    private String regCapital;
    /**
     * 所在地
     */
    @Schema(description = "所在地")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "reg_city", length = 64)
    private String regCity;
    /**
     * 所在区/县
     */
    @Schema(description = "所在区/县")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "reg_county", length = 64)
    private String regCounty;
    /**
     * 注册地址
     */
    @Schema(description = "注册地址")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "reg_address", length = 255)
    private String regAddress;
    /**
     * 企业性质
     */
    @Schema(description = "企业性质")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "comp_nature", length = 10)
    private String compNature;
    /**
     * 所属行业
     */
    @Schema(description = "所属行业")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "industry", length = 10)
    private String industry;
    /**
     * 公司简介
     */
    @Schema(description = "公司简介")
    @ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
    @Column(name = "comp_introduction")
    private String compIntroduction;
    /**
     * 纳入标准类型ID
     */
    @Schema(description = "纳入标准类型ID")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "standard_type_id", length = 10)
    private String standardTypeId;
    /**
     * 纳入标准类型名称
     */
    @Schema(description = "纳入标准类型名称")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "standard_type", length = 255)
    private String standardType;
    /**
     * 是否愿意辅导
     */
    @Schema(description = "是否愿意辅导")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "is_guidance", length = 10)
    private String isGuidance;
    /**
     * 是否愿意公示
     */
    @Schema(description = "是否愿意公示")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "is_open", length = 10)
    private String isOpen;
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
    /**
     * 状态;0：保存不提交，1：提交审核，2：审核不通过，3：审核通过
     */
    @Schema(description = "状态, 状态;0：保存不提交，1：提交审核，2：审核不通过，3：审核通过")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "status", length = 10)
    private String status;


}
