package org.hswebframework.isdp.sns.web;

import io.swagger.v3.oas.annotations.Parameter;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.sns.entity.SnsDomain;
import org.hswebframework.isdp.sns.service.SnsDomainService;
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
 * 领域管理
 * 领域相当于 顶级导航
 * 领域标签相当于 二级标题
 * 在当前的sns设计中，没有任何的层级概念，只是单纯的（领域对标签），1：n的关系，且标签可以从属于多个领域
 */
@RestController
@RequestMapping("/sns/SnsDomain")
@Resource(id = "SnsDomain", name = "领域管理")
@Authorize
public class SnsDomainCmd implements ReactiveCrudController<SnsDomain, String> {

    @Autowired
    private SnsDomainService snsDomainService;

    @Override
    public ReactiveRepository<SnsDomain, String> getRepository() {
        return snsDomainService.getRepository();
    }

    // 1 匿名访问，获取所有的领域
    @GetMapping("/getALlDomainList")
    @Authorize(ignore = true)
    public Mono<List<SnsDomain>> getALlDomainList(@PathVariable("status") @Parameter(name = "status" ,description = "状态") String status,
                                                  @PathVariable("type") @Parameter(name = "type" ,description = "类型" )String type){
       return snsDomainService.createQuery()
                .where(SnsDomain::getDomainStatus,status)
                .and(SnsDomain::getDomainType,type)
                .fetch()
                .collectList();
    }
}