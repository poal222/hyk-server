package org.hswebframework.isdp.sns.entity;

import org.hswebframework.web.api.crud.entity.GenericEntity;

//@Table(name="sns_vote")
public class SnsVote extends GenericEntity<String> {

	/** 投票者 id */
	private String userid;
	/** 0：赞成，1：反对 */
	private String type;
	/** 0：帖子，1：回帖，2：用户，3：标签 */
	private String datatype;
	/** 数据实体 id */
	private String dataid;
}
