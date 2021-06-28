package org.hswebframework.isdp.sns.web;

import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.sns.entity.SnsFollow;
import org.hswebframework.isdp.sns.service.SnsFollowService;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}