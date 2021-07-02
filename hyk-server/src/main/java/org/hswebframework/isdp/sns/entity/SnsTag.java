package org.hswebframework.isdp.sns.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.JDBCType;

@Table(name = "sns_tag")
@Setter
@Getter
public class SnsTag extends GenericEntity<String> {

	/** 标签引用（帖子/用户自评等）计数 */
	/**
	 * 评论内容
	 */
	@Column
	@Schema(description = "评论内容")
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	private String tagReferenceCount;
	/** 标签回帖计数 */
	/**
	 * 评论内容
	 */
	@Column
	@Schema(description = "评论内容")
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	private String tagCommentCount;
	/** 标签关注者计数 */
	/**
	 * 评论内容
	 */
	@Column
	@Schema(description = "评论内容")
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	private String tagFollowerCount;
	/** 标签链接引用计数 */
	/**
	 * 评论内容
	 */
	@Column
	@Schema(description = "评论内容")
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	private String tagLinkCount;
	/** 标签标题 */
	/**
	 * 评论内容
	 */
	@Column
	@Schema(description = "评论内容")
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	private String tagTitle;
	/** 标签访问路径 */
	/**
	 * 评论内容
	 */
	@Column
	@Schema(description = "评论内容")
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	private String tagURI;
	/** 标签描述 */
	/**
	 * 评论内容
	 */
	@Column
	@Schema(description = "评论内容")
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	private String tagDescription;
	/** 标签图标路径 */
	/**
	 * 评论内容
	 */
	@Column
	@Schema(description = "评论内容")
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	private String tagIconPath;
	/** 标签 CSS */
	/**
	 * 评论内容
	 */
	@Column
	@Schema(description = "评论内容")
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	private String tagCSS;
	/** 0：正常，1：封禁 */
	/**
	 * 评论内容
	 */
	@Column
	@Schema(description = "评论内容")
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	private String tagStatus;
	/** 标签点赞计数 */
	/**
	 * 评论内容
	 */
	@Column
	@Schema(description = "评论内容")
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	private String tagGoodCnt;
	/** 标签点踩计数 */
	/**
	 * 评论内容
	 */
	@Column
	@Schema(description = "评论内容")
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	private String tagBadCnt;
	/** 标签 SEO 标题 */
	/**
	 * 评论内容
	 */
	@Column
	@Schema(description = "评论内容")
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	private String tagSeoTitle;
	/** 标签 SEO 关键字 */
	/**
	 * 评论内容
	 */
	@Column
	@Schema(description = "评论内容")
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	private String tagSeoKeywords;
	/** 标签 SEO 描述 */
	/**
	 * 评论内容
	 */
	@Column
	@Schema(description = "评论内容")
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	private String tagSeoDesc;
	/** 标签随机数，用于快速选择随机标签 */
	/**
	 * 评论内容
	 */
	@Column
	@Schema(description = "评论内容")
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	private String tagRandomDouble;
	/** 标签广告 */
	/**
	 * 评论内容
	 */
	@Column
	@Schema(description = "评论内容")
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	private String tagAd;
	/** 是否显示全站侧边栏广告 */
	/**
	 * 评论内容
	 */
	@Column
	@Schema(description = "评论内容")
	@ColumnType(jdbcType = JDBCType.LONGNVARCHAR)
	private String tagShowSideAd;


}