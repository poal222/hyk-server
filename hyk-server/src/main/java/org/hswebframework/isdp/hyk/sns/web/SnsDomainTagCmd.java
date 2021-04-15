package org.hswebframework.isdp.hyk.sns.web;

import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.hyk.sns.enetity.SnsDomainTag;
import org.hswebframework.isdp.hyk.sns.service.SnsDomainTagService;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 领域标签管理
 */
@RestController
public class SnsDomainTagCmd implements ReactiveCrudController<SnsDomainTag, String> {

    @Autowired
    private SnsDomainTagService SnsDomainTagService;

    @Override
    public ReactiveRepository<SnsDomainTag, String> getRepository() {
        return SnsDomainTagService.getRepository();
    }
}