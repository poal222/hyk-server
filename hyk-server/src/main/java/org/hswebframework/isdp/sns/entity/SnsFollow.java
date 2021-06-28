package org.hswebframework.isdp.sns.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 关注表
 */
@Table(name="Sns_follow")
public class SnsFollow extends GenericEntity<String> {

	/**
	 * 关注者 id
	 */
	@Column
	@Schema(description = "关注者 id")
	private String followerId;
	/**
	 *  被关注者id
	 */
	@Column
	@Schema(description = "被关注者id")
	private String followingId;
	/**
	 * 0：用户，1：标签，2：帖子收藏，3：帖子关注
	 */
	@Column
	@Schema(description = " 0：用户，1：标签，2：帖子收藏，3：帖子关注")
	private String followingType;
}

