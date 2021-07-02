package org.hswebframework.isdp.sns.web;

import io.swagger.v3.oas.annotations.Parameter;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.sns.entity.SnsDomainTag;
import org.hswebframework.isdp.sns.entity.SnsTag;
import org.hswebframework.isdp.sns.service.SnsTagService;
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
 * 标签管理
 * 所有的标签的统一管理
 */
@RestController
@RequestMapping("/sns/SnsTag")
@Resource(id = "SnsTag", name = "标签管理")
@Authorize
public class SnsTagCmd implements ReactiveCrudController<SnsTag, String> {

    @Autowired
    private SnsTagService SnsTagService;

    @Override
    public ReactiveRepository<SnsTag, String> getRepository() {
        return SnsTagService.getRepository();
    }


    // 1 匿名访问，获取全部的tags
    @GetMapping("/getALlTagsList")
    @Authorize(ignore = true)
    public Mono<List<SnsTag>> getALlTagsList(@PathVariable("status") @Parameter(name = "status" ,description = "状态") String status){
        return SnsTagService.createQuery()
                .where(SnsTag::getTagStatus,status)
                .fetch()
                .collectList();
    }
}