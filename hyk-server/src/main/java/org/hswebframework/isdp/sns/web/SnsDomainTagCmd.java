package org.hswebframework.isdp.sns.web;

import io.swagger.v3.oas.annotations.Parameter;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.sns.entity.SnsDomain;
import org.hswebframework.isdp.sns.entity.SnsDomainTag;
import org.hswebframework.isdp.sns.entity.SnsTag;
import org.hswebframework.isdp.sns.service.SnsDomainTagService;
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
 * 领域标签管理
 */
@RestController
@RequestMapping("/sns/SnsDomainTag")
@Resource(id = "SnsDomainTag", name = "领域标签管理")
@Authorize
public class SnsDomainTagCmd implements ReactiveCrudController<SnsDomainTag, String> {

    @Autowired
    private SnsDomainTagService SnsDomainTagService;
    @Autowired
    private SnsTagService snsTagService;

    @Override
    public ReactiveRepository<SnsDomainTag, String> getRepository() {
        return SnsDomainTagService.getRepository();
    }

    // 1 匿名访问，获取某个领域的tags
    @GetMapping("/getDomainTagsList")
    @Authorize(ignore = true)
    public Mono<List<SnsTag>> getDomainTagsList(@PathVariable("domainId")
                                                   @Parameter(name = "domainId" ,description = "领域id")
                                                           String domainId
                                                  ){
         return snsTagService.createQuery()
                .in(SnsTag::getId,SnsDomainTagService.createQuery()
                .where(SnsDomainTag::getDomainOid,domainId)
                .fetch()
                .map(SnsDomainTag::getTagOid))
                 .fetch()
                 .collectList();
    }
}