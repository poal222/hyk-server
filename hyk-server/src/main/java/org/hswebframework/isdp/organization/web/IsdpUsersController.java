package org.hswebframework.isdp.organization.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hswebframework.isdp.organization.UserDetailService;
import org.hswebframework.isdp.organization.entity.UserDetail;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.authorization.exception.UnAuthorizedException;
import org.hswebframework.web.system.authorization.api.service.reactive.ReactiveUserService;
import org.hswebframework.web.system.authorization.defaults.service.DefaultReactiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/isdpUsers")
@RestController
@Resource(id = "isdpUsers", name = "用户管理")
@Tag(name = "用户管理")
public class IsdpUsersController {

  @Autowired
	private UserDetailService userDetailService;
  @Autowired
	private ReactiveUserService reactiveUserService;

	@Autowired
	private DefaultReactiveUserService defaultReactiveUserService;


	/**
	 * 获取当前登录用户详情
	 *
	 * @return 用户详情
	 */
	@GetMapping
	@Operation(summary = "获取当前登录用户详情")
	public Mono<UserDetail> getCurrentLoginUserDetail() {
		return Authentication
				.currentReactive()
				.switchIfEmpty(Mono.error(UnAuthorizedException::new))
				.flatMap(autz -> userDetailService.findUserDetail(autz.getUser().getId()));
	}

	/**
	 * 保存当前用户详情
	 *
	 * @return 用户详情
	 */
	@PutMapping
	@Operation(summary = "保存当前用户详情")
	public Mono<Void> saveUserDetail(@RequestBody Mono<UserDetail> request) {
		return Authentication
				.currentReactive()
				.zipWith(request)
				.switchIfEmpty(Mono.error(UnAuthorizedException::new))
				.flatMap(tp2 -> userDetailService.saveUserDetail(tp2.getT1().getUser().getId(), tp2.getT2()));
	}

}
