package org.hswebframework.isdp.hyk.sns.web;

import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.hyk.sns.enetity.SnsTag;
import org.hswebframework.isdp.hyk.sns.service.SnsTagService;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标签管理
 * 所有的标签的统一管理
 */
@RestController
public class SnsTagCmd implements ReactiveCrudController<SnsTag, String> {

    @Autowired
    private SnsTagService SnsTagService;

    @Override
    public ReactiveRepository<SnsTag, String> getRepository() {
        return SnsTagService.getRepository();
    }
}