package org.hswebframework.isdp.hyk.sns.enetity;

import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Table;

@Table(name="symphony_follow")
public class SymphonyFollow extends GenericEntity<String> {

    /**
     * 关注者 id
     */
    private String followerid;
    /**
     * 关注实体 id
     */
    private String followingid;
    /**
     * 0：用户，1：标签，2：帖子收藏，3：帖子关注
     */
    private String followingtype;
}
