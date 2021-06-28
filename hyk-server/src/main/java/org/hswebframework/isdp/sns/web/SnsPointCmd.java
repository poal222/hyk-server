package org.hswebframework.isdp.sns.web;

import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;

import org.hswebframework.isdp.sns.entity.SnsPointtransfer;
import org.hswebframework.isdp.sns.service.SnsPointtransferService;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 积分管理
 */
@RestController
@RequestMapping("/sns/SnsPoint")
@Resource(id = "SnsPoint", name = "积分管理")
@Authorize
public class SnsPointCmd implements ReactiveCrudController<SnsPointtransfer, String> {

    @Autowired
    private SnsPointtransferService snsPointtransferService;

    @Override
    public ReactiveRepository<SnsPointtransfer, String> getRepository() {
        return snsPointtransferService.getRepository();
    }
}