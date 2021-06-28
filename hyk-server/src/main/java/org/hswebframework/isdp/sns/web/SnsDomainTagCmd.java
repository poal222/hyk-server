package org.hswebframework.isdp.sns.web;

import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.sns.entity.SnsDomainTag;
import org.hswebframework.isdp.sns.service.SnsDomainTagService;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Override
    public ReactiveRepository<SnsDomainTag, String> getRepository() {
        return SnsDomainTagService.getRepository();
    }
}