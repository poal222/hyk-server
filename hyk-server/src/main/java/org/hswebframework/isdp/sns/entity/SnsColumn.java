package org.hswebframework.isdp.sns.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.JDBCType;


/**
 * 专栏管理
 * 付费专栏是今日头条平台为创作者打造的一种新的内容变现形式，专栏作者可以发布付费图文、视频任意一种形式（也可以同一个专栏多种形式混合）的专栏内容，自行【标定价格】，用户按需付费购买后，专栏作者即可获得收益分成。
 * 进"电脑端头条号后台 - 进阶创作 - 付费专栏 - 我的专栏 - 专栏管理 - 创建专栏 - 填写专栏信息”提交专栏并审核。
 * 专栏提交后需要审核，审核时间为2小时左右，结果会通过电脑全头条号后台通知，若未通过审核，请根据通知中的原因修改专栏信息，并重新提交。
 */
@Table(name = "Sns_Column")
@Getter
@Setter
public class SnsColumn extends GenericEntity<String> {
	/**
	 * 用户id
	 */
	@Column(length = 64)
	@ColumnType(jdbcType = JDBCType.VARCHAR)
	@Schema(description = "专栏作者id")
	private String userId;


	/**
	 * 专栏名称
	 */
	@Column(length = 64)
	@ColumnType(jdbcType = JDBCType.VARCHAR)
	@Schema(description = "专栏名称")
	private String name;
	/**
	 * 专栏封面
	 */
	@Column
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	@Schema(description = "专栏封面")
	private String cover;
	/**
	 * 专栏状态
	 */
	@Column(length = 3)
	@ColumnType(jdbcType = JDBCType.VARCHAR)
	@Schema(description = "专栏状态:0：审核不通过。1：审核通过，2：被注销,3:待审核")
	private String status;
	/**
	 * 专栏付费价格
	 */
	@Column(length = 64)
	@ColumnType(jdbcType = JDBCType.VARCHAR)
	@Schema(description = "专栏价格，付费元")
	private String price;
	/**
	 * 法律确认书
	 */
	@Column(length = 5)
	@ColumnType(jdbcType = JDBCType.VARCHAR)
	@Schema(description = "法律确认书")
	private String confirm;
	/**
	 * 版权类型
	 */
	@Column(length = 64)
	@ColumnType(jdbcType = JDBCType.VARCHAR)
	@Schema(description = "版权类型：origin：原创，copyright：被授权")
	private String copyright;
	/**
	 * 版权类型
	 */
	@Column(length = 255)
	@ColumnType(jdbcType = JDBCType.VARCHAR)
	@Schema(description = "适合读者")
	private String scope;

	/**
	 * 发表文章数
	 */
	@Column(length = 64)
	@ColumnType(jdbcType = JDBCType.VARCHAR)
	@Schema(description = "发表文章数")
	private String publishedArticles;

	/**
	 * 购买的文章数
	 */
	@Column(length = 64)
	@ColumnType(jdbcType = JDBCType.VARCHAR)
	@Schema(description = "购买的文章数")
	private String PurchaseArticles;
}
