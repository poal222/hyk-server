package org.hswebframework.isdp.hyk.sns.enetity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Table(name="symphony_reward")
public class SymphonyReward implements Serializable,Cloneable {

    /** 打赏人 id */
    private  String senderid;
    /** 数据实体 id */
    private  String dataid;
    /** 0：帖子，1：回帖，2：用户 */
    private  String type;
}
