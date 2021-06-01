package org.hswebframework.isdp.sns.entity;

import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Table;

/**
 *  领域表
 */
@Table(name="SNS_DOMAIN")
public class SnsDomain extends GenericEntity<String> {

	/** 领域标题 */
	private String domainTitle;
	/** 领域访问路径 */
	private String domainURI;
	/** 领域描述 */
	private String domainDescription;
	/** 领域类型 */
	private String domainType;
	/** 领域排序 */
	private String domainSort;
	/** 0：作为导航，1：不作为导航 */
	private String domainNav;
	/** 领域标签计数 */
	private String domainTagCnt;
	/** 领域图片路径 */
	private String domainIconPath;
	/** 领域 CSS */
	private String domainCSS;
	/** 状态 0：正常，1：封禁*/
	private String domainStatus;
	/** seo标题 */
	private String domainSeoTitle;
	/** 领域 SEO 关键字 */
	private String domainSeoKeywords;
	/** 领域 SEO 描述 */
	private String domainSeoDesc;

}