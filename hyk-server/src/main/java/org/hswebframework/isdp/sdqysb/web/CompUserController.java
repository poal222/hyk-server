package org.hswebframework.isdp.sdqysb.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hswebframework.isdp.organization.UserDetailService;
import org.hswebframework.isdp.organization.entity.UserDetail;
import org.hswebframework.isdp.sdqysb.service.CompUserService;
import org.hswebframework.isdp.sdqysb.vo.CompUserVo;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.authorization.exception.UnAuthorizedException;
import org.hswebframework.web.system.authorization.api.service.reactive.ReactiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/sdsb/compUsers")
@RestController
@Resource(id = "sdsb.compUsers", name = "企业用户管理")
@Tag(name = "企业用户管理")
public class CompUserController {

    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private ReactiveUserService reactiveUserService;
    @Autowired
    private CompUserService compUserService;

    /**
     * 获取企业用户详情
     * 场景如下：
     * 1、审批人可以查看当前具体的申请账户的的信息
     * 2、账号申请人，明细查询
     *
     * @param id 企业用户id
     * @return 用户详情
     */
    @GetMapping
    @Operation(summary = "获取企业用户详情")
    public Mono<CompUserVo> getCompUserDetail(@Parameter(name = "id") @RequestParam(name = "id") String id) {
        CompUserVo compUserVo = new CompUserVo();
        return compUserService.findById(id)
                .map(compUser -> {
                    compUserVo.setId(compUser.getId());
                    compUserVo.setCompName(compUser.getCompName());
                    compUserVo.setUsccId(compUser.getUsccId());
                    compUserVo.setCompStatus(compUser.getCompStatus());
                    compUserVo.setCompType(compUser.getCompType());
                    compUserVo.setRecommendId(compUser.getRecommendId());
                    compUserVo.setLastUpdateTime(compUser.getLastUpdateTime());
                    compUserVo.setCompUserId(compUser.getCompUserId());
                    return compUserVo;
                }).flatMap(compUserVo1 -> {
                    return reactiveUserService.findById(compUserVo1.getCompUserId())
                            .map(userEntity -> {
                                compUserVo.setCompUserId(userEntity.getId());
                                compUserVo.setCompName(userEntity.getName());
                                compUserVo.setCompUserType(userEntity.getType());
                                return compUserVo1;
                            });
                });
    }

    /**
     * 保存企业用户详情
     *
     * @return 用户详情
     */
    @PutMapping
    @Operation(summary = "保存企业用户详情")
    public Mono<Void> saveUserDetail(@RequestBody Mono<UserDetail> request) {
        return Authentication
                .currentReactive()
                .zipWith(request)
                .switchIfEmpty(Mono.error(UnAuthorizedException::new))
                .flatMap(tp2 -> userDetailService.saveUserDetail(tp2.getT1().getUser().getId(), tp2.getT2()));
    }

}
