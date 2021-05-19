package org.hswebframework.isdp.sdqysb.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.JDBCType;

@Table(name="comp_stock")
@Getter
@Setter
public class CompStock extends GenericEntity<String> {
    /** 版本 */
    /**
     * 版本
     */
    @Schema(description = "版本")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "version", length = 10)
    private String version ;
    /** 股东一 */
    /**
     *股东一
     */
    @Schema(description = "股东一 ")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "first_stockholder", length = 10)
    private String firstStockholder ;
    /** 股东一持股数量 */
    /**
     * 提交版本;每次审核通过后，作为一个新版本
     */
    @Schema(description = "股东一持股数量")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "first_atm", length = 10)
    private String firstAtm ;
    /** 股东一持股比例 */
    /**
     * 提交版本;每次审核通过后，作为一个新版本
     */
    @Schema(description = "股东一持股比例")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "first_rate", length = 10)
    private String firstRate ;
    /** 股东一所有者性质 */
    @Schema(description = "股东一所有者性质")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "first_type", length = 10)
    private String firstType ;
    /** 股东二 */
    /**
     * 提交版本;每次审核通过后，作为一个新版本
     */
    @Schema(description = "股东二")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "second_stockholder", length = 10)
    private String secondStockholder ;
    /** 股东二持股数量 */
    /**
     * 提交版本;每次审核通过后，作为一个新版本
     */
    @Schema(description = "股东二持股数量")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "second_atm", length = 10)
    private String secondAtm ;
    /** 股东二持股比例 */
    /**
     * 提交版本;每次审核通过后，作为一个新版本
     */
    @Schema(description = "股东二持股比例")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "second_rate", length = 10)
    private String secondRate ;
    /** 股东二所有者性质 */
    /**
     * 提交版本;每次审核通过后，作为一个新版本
     */
    @Schema(description = "股东二所有者性质")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "second_type", length = 10)
    private String secondType ;
    /** 股东三 */
    /**
     * 提交版本;每次审核通过后，作为一个新版本
     */
    @Schema(description = "股东三")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "third_stockholder", length = 10)
    private String thirdStockholder ;
    /** 股东三持股数量 */
    /**
     * 提交版本;每次审核通过后，作为一个新版本
     */
    @Schema(description = "股东三持股数量")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "third_atm", length = 10)
    private String thirdAtm ;
    /** 股东三持股比例 */
    /**
     * 提交版本;每次审核通过后，作为一个新版本
     */
    @Schema(description = "股东三持股比例")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "third_rate", length = 10)
    private String thirdRate ;
    /** 股东三所有者性质 */
    /**
     * 股东三所有者性质
     */
    @Schema(description = "股东三所有者性质")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "third_type", length = 10)
    private String third_type ;
    /** 股东四 */
    /**
     * 股东四
     */
    @Schema(description = "股东四")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "forth_stockholder", length = 10)
    private String forthStockholder ;
    /**
     * 股东四持股数量
     */
    @Schema(description = "股东四持股数量")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "forth_atm", length = 10)
    private String forthAtm ;
    /**
     * 股东四持股比例
     */
    @Schema(description = "股东四持股比例")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "forth_rate", length = 10)
    private String forthRate ;
    /** 股东四所有者性质 */
    /**
     * 股东四所有者性质
     */
    @Schema(description = "股东四所有者性质")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "forth_type", length = 10)
    private String forthType ;
    /** 股东五 */
    /**
     * 股东五
     */
    @Schema(description = "提交版本")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "fivth_stockholder", length = 10)
    private String fivthStockholder ;
    /** 股东五持股数量 */
    /**
     * 股东五持股数量
     */
    @Schema(description = "股东五持股数量")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "fivth_atm", length = 10)
    private String fivthAtm ;
    /** 股东五持股比例 */
    /**
     * 股东五持股比例
     */
    @Schema(description = "股东五持股比例")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "fivth_rate", length = 10)
    private String fivthRate ;
    /** 股东五所有者性质 */
    /**
     * 股东五所有者性质
     */
    @Schema(description = "股东五所有者性质")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "fivth_type", length = 10)
    private String fivthType ;
    /**
     * 是否战略投资
     */
    @Schema(description = "是否战略投资")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "isQSI", length = 10)
        private String isqsi ;
    /** 主要投资者持股比例 */
    /**
     *主要投资者持股比例
     */
    @Schema(description = "主要投资者持股比例")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "masterRate", length = 10)
    private String masterrate ;
    /** 最新一轮 估值 */
    /**最新一轮 估值
     */
    @Schema(description = "最新一轮 估值")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "valuation", length = 10)
    private String valuation ;
    /** 公司id */
    /**
     * 公司id
     */
    @Schema(description = "公司id")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "comp_id", length = 10)
    private String compId ;
}
