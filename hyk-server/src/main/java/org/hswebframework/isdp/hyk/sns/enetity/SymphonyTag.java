package org.hswebframework.isdp.hyk.sns.enetity;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name="symphony_tag")
public class SymphonyTag extends GenericEntity<String> {

    /** 标签引用（帖子/用户自评等）计数 */
    private  String  tagreferencecount ;
    /** 标签回帖计数 */
    private  String  tagcommentcount ;
    /** 标签关注者计数 */
    private  String  tagfollowercount ;
    /** 标签链接引用计数 */
    private  String  taglinkcount ;
    /** 标签标题 */
    private  String  tagtitle ;
    /** 标签访问路径 */
    private  String  taguri ;
    /** 标签描述 */
    private  String  tagdescription ;
    /** 标签图标路径 */
    private  String  tagiconpath ;
    /** 标签 CSS */
    private  String  tagcss ;
    /** 0：正常，1：封禁 */
    private  String  tagstatus ;
    /** 标签点赞计数 */
    private  String  taggoodcnt ;
    /** 标签点踩计数 */
    private  String  tagbadcnt ;
    /** 标签 SEO 标题 */
    private  String  tagseotitle ;
    /** 标签 SEO 关键字 */
    private  String  tagseokeywords ;
    /** 标签 SEO 描述 */
    private  String  tagseodesc ;
    /** 标签随机数，用于快速选择随机标签 */
    private  String  tagrandomdouble ;
    /** 标签广告 */
    private  String  tagad ;
    /** 是否显示全站侧边栏广告 */
    private  String  tagshowsidead ;


}