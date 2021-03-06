package org.hswebframework.isdp.sns.entity;

import org.hswebframework.web.api.crud.entity.GenericEntity;

//@Table(name="sns_visit")
public class SnsVisit extends GenericEntity<String> {

	/** 浏览链接 */
	private String visiturl;
	/** IP */
	private String visitip;
	/** User-Agent */
	private String visitua;
	/** 城市 */
	private String visitcity;
	/** 设备唯一标识 */
	private String visitdeviceid;
	/** 浏览者 id */
	private String visituserid;
	/** 上游链接 */
	private String visitrefererurl;
	/** 统计时间 */
	private String visitcreated;
	/** 统计过期时间 */
	private String visitexpired;
}
