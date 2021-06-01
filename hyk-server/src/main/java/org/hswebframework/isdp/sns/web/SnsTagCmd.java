package org.hswebframework.isdp.sns.web;


import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.sns.entity.SnsTag;
import org.hswebframework.isdp.sns.service.SnsTagService;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标签管理
 * 标签是整个sns的灵魂，可以实现话题，分类，查询等
 * 但是标签同于领域，领域相当于传统的栏目的概念，
 * 但是标签仅仅是自定义分类
 */
@RestController
@RequestMapping("/sns/tags")
public class SnsTagCmd implements ReactiveCrudController<SnsTag, String> {


    @Autowired
    private SnsTagService SnsTagService;

    @Override
    public ReactiveRepository<SnsTag, String> getRepository() {
        return SnsTagService.getRepository();
    }


}