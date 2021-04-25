//package org.hswebframework.isdp.hyk.sns.web;
//
//import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
//import org.hswebframework.isdp.hyk.sns.enetity.SnsDomain;
//import org.hswebframework.isdp.hyk.sns.service.SnsDomainService;
//import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 领域管理
// * 领域相当于 顶级导航
// * 领域标签相当于 二级标题
// * 在当前的sns设计中，没有任何的层级概念，只是单纯的（领域对标签），1：n的关系，且标签可以从属于多个领域
// */
//@RestController
//public class SnsDomainCmd implements ReactiveCrudController<SnsDomain, String> {
//
//    @Autowired
//    private SnsDomainService SnsDomainService;
//
//    @Override
//    public ReactiveRepository<SnsDomain, String> getRepository() {
//        return SnsDomainService.getRepository();
//    }
//}