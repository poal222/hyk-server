package org.hswebframework.isdp.hyk.sns.enetity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Table(name="Sns_tag_article")
public class SnsTagArticle implements Serializable,Cloneable{
   
    /** 帖子 id */
    private  String  articleOid ;
    /** 标签 id */
    private  String  tagOid ;
    /** 帖子回帖计数 */
    private  String  articlecommentcount ;
    /** 帖子最近一次回帖时间 */
    private  String  articlelatestcmttime ;
    /** 帖子 Reddit 算法评分 */
    private  String  redditscore ;
    /** 0：帖子不是优选，1：帖子是优选 */
    private  String  articleperfect ;

}