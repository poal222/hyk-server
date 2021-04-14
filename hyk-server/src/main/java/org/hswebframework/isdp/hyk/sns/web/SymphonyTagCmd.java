package org.hswebframework.isdp.hyk.sns.web;

import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.hyk.sns.enetity.SymphonyDomain;
import org.hswebframework.isdp.hyk.sns.enetity.SymphonyTag;
import org.hswebframework.isdp.hyk.sns.service.SymphonyDomainService;
import org.hswebframework.isdp.hyk.sns.service.SymphonyTagService;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标签管理
 */
@RestController
public class SymphonyTagCmd implements ReactiveCrudController<SymphonyTag, String> {

    @Autowired
    private SymphonyTagService symphonyTagService;

    @Override
    public ReactiveRepository<SymphonyTag, String> getRepository() {
        return symphonyTagService.getRepository();
    }
}