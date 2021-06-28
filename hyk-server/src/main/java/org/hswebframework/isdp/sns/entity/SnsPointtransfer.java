package org.hswebframework.isdp.sns.entity;

import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Table;

/**
 * 资金流水表
 * @author 王岗
 */
@Table(name="sns_pointtransfer")
public class SnsPointtransfer extends GenericEntity<String> {

	/** 源账户 id */
	private String fromid;
	/** 目标账户 id */
	private String toid;
	/** 转账金额 */
	private String sum;
	/** 源账户余额 */
	private String frombalance;
	/** 目标账户余额 */
	private String tobalance;
	/** 转账时间 */
	private String time;
	/** 转账类型 */
	private String type;
	/** 数据实体 id */
	private String dataid;
	/** 备注 */
	private String memo;
}