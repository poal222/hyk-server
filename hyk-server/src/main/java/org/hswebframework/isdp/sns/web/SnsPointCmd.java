//package org.hswebframework.isdp.hyk.sns.web;
//
//import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
//import org.hswebframework.isdp.hyk.sns.enetity.SnsPointtransfer;
//import org.hswebframework.isdp.hyk.sns.enetity.SnsTag;
//import org.hswebframework.isdp.hyk.sns.service.SnsPointtransferService;
//import org.hswebframework.isdp.hyk.sns.service.SnsTagService;
//import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 积分管理
// */
//@RestController
//public class SnsPointCmd implements ReactiveCrudController<SnsPointtransfer, String> {
//
//    @Autowired
//    private SnsPointtransferService snsPointtransferService;
//
//    @Override
//    public ReactiveRepository<SnsPointtransfer, String> getRepository() {
//        return snsPointtransferService.getRepository();
//    }
//}