package org.hswebframework.isdp.sns.entity;

import java.io.Serializable;


//@Table(name="Sns_reward")
public class SnsReward implements Serializable, Cloneable {

	/** 打赏人 id */
	private String senderid;
	/** 数据实体 id */
	private String dataid;
	/** 0：帖子，1：回帖，2：用户 */
	private String type;
}
