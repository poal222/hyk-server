package org.hswebframework.isdp.hyk.sns.enetity;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * 修订表
 */
@Table(name="SYMPHONY_REVISION")
public class SymphonyRevision implements Serializable,Cloneable{

    /** 0：帖子，1：回帖 */
    private String revisiondatatype  ;
    /** 数据实体 id */
    private String revisiondataid ;
    /** 修订内容 */
    private String revisiondata ;
    /** 作者id */
    private String revisionauthorid ;
}
