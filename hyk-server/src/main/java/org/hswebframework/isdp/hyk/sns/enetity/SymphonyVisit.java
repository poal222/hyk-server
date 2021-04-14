package org.hswebframework.isdp.hyk.sns.enetity;

import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name="symphony_visit")
public class SymphonyVisit extends GenericEntity<String> {

    /** 浏览链接 */
    private  String visiturl;
    /** IP */
    private  String visitip;
    /** User-Agent */
    private  String visitua;
    /** 城市 */
    private  String visitcity;
    /** 设备唯一标识 */
    private  String visitdeviceid;
    /** 浏览者 id */
    private  String visituserid;
    /** 上游链接 */
    private  String visitrefererurl;
    /** 统计时间 */
    private  String visitcreated;
    /** 统计过期时间 */
    private  String visitexpired;
}
