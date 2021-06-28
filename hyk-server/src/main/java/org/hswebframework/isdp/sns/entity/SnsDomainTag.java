package org.hswebframework.isdp.sns.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 领域 - 标签关联表
 */
@Table(name="Sns_domain_tag")
public class SnsDomainTag extends GenericEntity<String> {

	/** 领域 id */
	@Column
	@Schema(description = "领域 id ")
	private String domainOid;
	/** 标签 id */
	@Column
	@Schema(description = "标签 id")
	private String tagOid;


}