package org.hswebframework.isdp.sns.entity;

import org.hswebframework.web.api.crud.entity.GenericEntity;

//@Table(name = "sns_tag")
public class SnsTag extends GenericEntity<String> {

	/** 标签引用（帖子/用户自评等）计数 */
	private String tagReferenceCount;
	/** 标签回帖计数 */
	private String tagCommentCount;
	/** 标签关注者计数 */
	private String tagFollowerCount;
	/** 标签链接引用计数 */
	private String tagLinkCount;
	/** 标签标题 */
	private String tagTitle;
	/** 标签访问路径 */
	private String tagURI;
	/** 标签描述 */
	private String tagDescription;
	/** 标签图标路径 */
	private String tagIconPath;
	/** 标签 CSS */
	private String tagCSS;
	/** 0：正常，1：封禁 */
	private String tagStatus;
	/** 标签点赞计数 */
	private String tagGoodCnt;
	/** 标签点踩计数 */
	private String tagBadCnt;
	/** 标签 SEO 标题 */
	private String tagSeoTitle;
	/** 标签 SEO 关键字 */
	private String tagSeoKeywords;
	/** 标签 SEO 描述 */
	private String tagSeoDesc;
	/** 标签随机数，用于快速选择随机标签 */
	private String tagRandomDouble;
	/** 标签广告 */
	private String tagAd;
	/** 是否显示全站侧边栏广告 */
	private String tagShowSideAd;


}