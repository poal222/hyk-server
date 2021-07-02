package org.hswebframework.isdp.sns.web;

import io.swagger.v3.oas.annotations.Parameter;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.sns.entity.SnsDomain;
import org.hswebframework.isdp.sns.entity.SnsFollow;
import org.hswebframework.isdp.sns.service.SnsFollowService;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

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
@RequestMapping("/sns/SnsFollow")
@Resource(id = "SnsFollow", name = "关注信息管理")
@Authorize
public class SnsFollowCmd implements ReactiveCrudController<SnsFollow, String> {

    @Autowired
    private SnsFollowService snsFollowService;

    @Override
    public ReactiveRepository<SnsFollow, String> getRepository() {
        return snsFollowService.getRepository();
    }

    // 1、粉丝数量
    // 1 匿名访问，获取所有的领域
    @GetMapping("/getFansList")
    @Authorize(ignore = true)
    public Mono<List<SnsFollow>> getFansList(@PathVariable("userId") @Parameter(name = "userId" ,description = "被关注者id") String userId
                                             /**
                                              * 0：用户，1：标签，2：帖子收藏，3：帖子关注
                                              */                             ){
        return snsFollowService.createQuery()
                .where(SnsFollow::getFollowerId,userId)
                .and(SnsFollow::getFollowingType,"0")
                .fetch()
                .collectList();
    }
    // 2、关注数量
    @GetMapping("/getFollowList")
    @Authorize(ignore = true)
    public Mono<List<SnsFollow>> getFollowList(@PathVariable("userId") @Parameter(name = "userId" ,description = "关注者id") String userId
                                             /**
                                              * 0：用户，1：标签，2：帖子收藏，3：帖子关注
                                              */                             ){
        return snsFollowService.createQuery()
                .where(SnsFollow::getFollowingId,userId)
                .and(SnsFollow::getFollowingType,"0")
                .fetch()
                .collectList();
    }
    // 3、收藏数量
    @GetMapping("/getshoucangList")
    @Authorize(ignore = true)
    public Mono<List<SnsFollow>> getshoucangList(@PathVariable("userId") @Parameter(name = "userId" ,description = "收藏着id") String userId
                                               /**
                                                * 0：用户，1：标签，2：帖子收藏，3：帖子关注
                                                */                             ){
        return snsFollowService.createQuery()
                .where(SnsFollow::getFollowerId,userId)
                .and(SnsFollow::getFollowingType,"2")
                .fetch()
                .collectList();
    }
}