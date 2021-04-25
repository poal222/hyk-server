package org.hswebframework.isdp.sns.entity;

import org.hswebframework.web.api.crud.entity.GenericEntity;


//@Table(name="Sns_report")
public class SnsReport extends GenericEntity<String> {

	/** 举报人 id */
	private String reportuserid;
	/** 举报数据 id */
	private String reportdataid;
	/** 0：帖子，1：回帖，2：用户 */
	private String reportdatatype;
	/** 0：（内容）垃圾广告，1：（内容）色情低俗，2：（内容）违法违规，3：（内容）涉嫌侵权，4：（内容）人身攻击，5：（用户）冒充账号，6：（用户）垃圾广告账号，7：（用户）个人信息违规，49：其他 */
	private String reporttype;
	/** 举报情况备注 */
	private String reportmemo;
	/** 0：未处理，1：已处理 */
	private String reporthandled;
}
