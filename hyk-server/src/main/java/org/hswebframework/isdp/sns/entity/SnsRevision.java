package org.hswebframework.isdp.sns.entity;

import java.io.Serializable;

/**
 * 修订表
 */
//@Table(name="Sns_REVISION")
public class SnsRevision implements Serializable, Cloneable {

	/** 0：帖子，1：回帖 */
	private String revisiondatatype;
	/** 数据实体 id */
	private String revisiondataid;
	/** 修订内容 */
	private String revisiondata;
	/** 作者id */
	private String revisionauthorid;
}
