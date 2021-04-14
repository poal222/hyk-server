package org.hswebframework.isdp.hyk.sns.web;

import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.hyk.entity.TestEntity;
import org.hswebframework.isdp.hyk.sns.enetity.SymphonyDomain;
import org.hswebframework.isdp.hyk.sns.service.SymphonyDomainService;
import org.hswebframework.web.crud.service.GenericReactiveCacheSupportCrudService;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * 领域管理
 */
@RestController
public class SymphonyDomainCmd implements ReactiveCrudController<SymphonyDomain, String> {

    @Autowired
    private SymphonyDomainService symphonyDomainService;

    @Override
    public ReactiveRepository<SymphonyDomain, String> getRepository() {
        return symphonyDomainService.getRepository();
    }
}