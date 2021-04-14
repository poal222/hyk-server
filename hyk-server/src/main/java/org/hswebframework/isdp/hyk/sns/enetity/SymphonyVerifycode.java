package org.hswebframework.isdp.hyk.sns.enetity;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name="symphony_verifycode")
public class SymphonyVerifycode extends GenericEntity<String> {

    /** 接收用户 id */
    private String userid ;
    /** 0：邮件 */
    private String type ;
    /** 0：注册，1：重置密码 */
    private String biztype ;
    /** 接收者 */
    private String receiver ;
    /** 验证码 */
    private String code ;
    /** 0：未发送，1：已发送 */
    private String status ;
    /** 过期时间 */
    private Date expired ;


}