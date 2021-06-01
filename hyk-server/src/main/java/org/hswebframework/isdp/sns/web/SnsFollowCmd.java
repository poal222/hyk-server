package org.hswebframework.isdp.hyk.sns.web;

import org.hswebframework.isdp.hyk.sns.service.SnsFollowService;
import org.hswebframework.isdp.sns.entity.SnsFollow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 关注信息管理
 *  0：用户，1：标签，2：帖子收藏，3：帖子关注
 *  普及以下几个概念
 *  	1、A用户关注B用户，则A为B的粉丝
 *  	2、A和B互相关注
 *  	3、A收藏了那些帖子
 *  	4、A关注了那些帖子
 */
@RestController
@RequestMapping("/sns/follow")
public class SnsFollowCmd {

    @Autowired
    private SnsFollowService snsFollowService;
    /**
     * 1.1 A用户关注B用户，则A为B的粉丝
     */
    public void followUser(SnsFollow snsFollow){
        snsFollowService.save(Mono.just(snsFollow));
    }
    /**
     * 1.2 取消关注
     */
    public void unFollowUser(String id){
        snsFollowService.deleteById(Mono.just(id));
    }
    /**
     * 1.2 粉丝列表
     * @param followingid  查询的被关注着id
     * @param type 关注类型，参见类注释
     */
    public Flux<SnsFollow> fansList(String followingid, String type){
       return snsFollowService.createQuery()
                .where("followingid",followingid)
                .and("followingtype",type)
                 .fetch();
    }
    /**
     * 1.3 收藏列表
     * @param followerid  收藏者id
     */
    public Flux<SnsFollow> fansList(String followerid){
        return snsFollowService.createQuery()
                .where("followerid",followerid)
                .and("followingtype",2)
                .fetch();
    }
    /**
     * 1.4 关注的帖子列表
     * @param followerid  收藏者id
     */
    public Flux<SnsFollow> articleFollowList(String followerid){
        return snsFollowService.createQuery()
                .where("followerid",followerid)
                .and("followingtype",3)
                .fetch();
    }


}