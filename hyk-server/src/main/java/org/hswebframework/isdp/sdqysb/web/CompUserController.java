package org.hswebframework.isdp.sdqysb.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.organization.UserDetailService;
import org.hswebframework.isdp.organization.entity.UserDetail;
import org.hswebframework.isdp.sdqysb.entity.CompUser;
import org.hswebframework.isdp.sdqysb.service.CompUserService;
import org.hswebframework.isdp.sdqysb.vo.CompUserDetail;
import org.hswebframework.isdp.sdqysb.vo.CompUserVo;
import org.hswebframework.web.api.crud.entity.TransactionManagers;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.authorization.exception.UnAuthorizedException;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.hswebframework.web.system.authorization.api.entity.UserEntity;
import org.hswebframework.web.system.authorization.api.service.UserService;
import org.hswebframework.web.system.authorization.api.service.reactive.ReactiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequestMapping("/sdsb/compUsers")
@RestController
@Resource(id = "sdsb.compUsers", name = "企业用户管理")
@Tag(name = "企业用户管理")
public class CompUserController implements ReactiveCrudController<CompUser, String> {

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
    @Transactional(rollbackFor = Exception.class, transactionManager = TransactionManagers.r2dbcTransactionManager)
    public Mono<Integer> saveUserDetail(@RequestBody CompUserDetail compUserDetail) {
        String id = UUID.randomUUID().toString();
        CompUser compUser = new CompUser();
        UserEntity userEntity = new UserEntity();

            if ("null".equalsIgnoreCase(compUserDetail.getId())) {
                compUserDetail.setId(id);
            }
            compUser.setUsccId(compUserDetail.getUsccId());
            compUser.setId(compUserDetail.getId() == null ? id : compUserDetail.getId());
            compUser.setCompName(compUserDetail.getCompName());
            compUser.setCompType(compUserDetail.getCompType());
            compUser.setCompUserId(compUserDetail.getId() == null ? id : compUserDetail.getId());
            compUser.setRecommendId(compUserDetail.getRecommendId());
            compUser.setCompStatus(compUserDetail.getCompStatus());
          userEntity.setId(compUserDetail.getId() == null ? id : compUserDetail.getId());
            userEntity.setName(compUserDetail.getName());
            userEntity.setPassword(compUserDetail.getPassword());
            userEntity.setUsername(compUserDetail.getUsername());
            userEntity.setType("qyyh");
        return Mono.zip(
                compUserService.save(Mono.just(compUser)),
                reactiveUserService.saveUser(Mono.just(userEntity)),
                ((t1, t2) -> 1));
    }

    @Override
    public ReactiveRepository<CompUser, String> getRepository() {
        return compUserService.getRepository();
    }
}
