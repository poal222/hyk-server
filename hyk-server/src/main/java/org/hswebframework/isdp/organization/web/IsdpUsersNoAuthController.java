package org.hswebframework.isdp.organization.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hswebframework.isdp.organization.UserDetailService;
import org.hswebframework.isdp.organization.entity.UserDetail;
import org.hswebframework.isdp.organization.entity.UserDetailEntity;
import org.hswebframework.isdp.organization.vo.HUserDetail;
import org.hswebframework.isdp.organization.vo.IsdpPassword;
import org.hswebframework.utils.RandomUtil;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.authorization.exception.UnAuthorizedException;
import org.hswebframework.web.system.authorization.api.entity.UserEntity;
import org.hswebframework.web.system.authorization.api.service.reactive.ReactiveUserService;
import org.hswebframework.web.system.authorization.defaults.service.DefaultReactiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/isdpUsersRegister")
@RestController
@Tag(name = "用户注册管理（仅限于华医酷用户使用）")
@Authorize(ignore = true) // 不拦截
public class IsdpUsersNoAuthController {


    @Autowired
    private ReactiveUserService reactiveUserService;
    @Autowired
    private UserDetailService userDetailService;
	/**
	 * 用户详情注册信息保存
	 *
     * @return
     */
    @PostMapping("/registry")
    @Operation(summary = "注册信息保存")
    public Mono<Integer> saveRegistryUser(@RequestBody HUserDetail hUserDetail) {
      UserEntity userEntity =   hUserDetail.converToUserEntity();
        UserDetailEntity userDetailEntity=hUserDetail.converToUserDetailEntity();
        userDetailEntity.setId(userEntity.getId());
        return Mono.zip( reactiveUserService.saveUser(Mono.just(userEntity)),
                userDetailService.save(Mono.just(userDetailEntity)),
                (aBoolean, saveResult) -> 1
                );
    }
}
