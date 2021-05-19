package org.hswebframework.isdp.Verfiy;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.ezorm.rdb.mapping.defaults.SaveResult;
import org.hswebframework.isdp.Verfiy.entity.IsdpVerfiy;
import org.hswebframework.isdp.Verfiy.entity.VerfiyEmnu;
import org.hswebframework.isdp.Verfiy.service.IsdpVerfiyService;
import org.hswebframework.web.api.crud.entity.TransactionManagers;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 实名认证服务
 */
@Tag(name = "实名认证服务", description = "实名认证服务")
@RestController
@RequestMapping("/isdp/verfiy")
@Resource(id = "isdp-Verfiy", name = "实名认证服务")
@Authorize
public class IsdpVerfiyCmd implements ReactiveCrudController<IsdpVerfiy, String> {

    @Autowired
    private IsdpVerfiyService isdpVerfiyService;

    /**
     * [1]个人实名认证
     */
    @PostMapping("/verify_id_name")
    @Operation(summary = "[1]个人实名认证，", description = "身份证二要素身份实名认证-实名认证-身份证二要素实名认证")
    @Transactional(rollbackFor = Exception.class, transactionManager = TransactionManagers.r2dbcTransactionManager)
    public Mono<SaveResult> verifyIdName(@RequestBody IsdpVerfiy isdpVerfiy) {
        isdpVerfiy.setType(VerfiyEmnu.grrz);
        return Authentication
                .currentReactive()
                .flatMap(authentication -> {
                    isdpVerfiy.setUserId(authentication.getUser().getId());
                    return isdpVerfiyService.save(Mono.just(isdpVerfiy));
                });


    }

    /**
     * [2]企业实名认证
     */
    @PostMapping("/verify_comapny")
    @Operation(summary = "[2]企业实名认证，", description = "企业实名认证")
    @Transactional(rollbackFor = Exception.class, transactionManager = TransactionManagers.r2dbcTransactionManager)
    public Mono<SaveResult> verifyComapny(@RequestBody IsdpVerfiy isdpVerfiy) {

        isdpVerfiy.setType(VerfiyEmnu.qyrz);
        return isdpVerfiyService.save(Mono.just(isdpVerfiy));

    }

    @Override
    public ReactiveRepository<IsdpVerfiy, String> getRepository() {
        return isdpVerfiyService.getRepository();
    }
    //
    // [3]组织实名认证

}
