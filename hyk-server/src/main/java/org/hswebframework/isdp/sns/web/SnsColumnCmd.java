package org.hswebframework.isdp.sns.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.isdp.sns.entity.SnsColumn;
import org.hswebframework.isdp.sns.service.SnsColumnService;
import org.hswebframework.web.api.crud.entity.TransactionManagers;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 收费专栏
 * 付费专栏类似于收费栏目，是提供给自媒体的用来进行原创化、具备收益功能的模块，要求所有的 专栏文章必须是原创性或者被授权了的文章，具有排他性意义。
 * 专栏作者可以发布付费图文、视频任意一种形式（也可以同一个专栏多种形式混合）的专栏内容，自行【标定价格】，
 * 用户按需付费购买后，专栏作者即可获得收益分成。
 * 结构设计分为 收费专栏——专栏文章
 */
@RestController
@RequestMapping("/sns/column")
@Resource(id = "snscolumn", name = "收费专栏")
@Authorize
@Tag(name = "收费专栏")
public class SnsColumnCmd implements ReactiveCrudController<SnsColumn, String> {

	@Autowired
	private SnsColumnService snsColumnService;


	@Override
	public ReactiveRepository<SnsColumn, String> getRepository() {
		return snsColumnService.getRepository();
	}
	@GetMapping("/changeState")
	@Operation(  summary = "专栏状态:0：审核不通过。1：审核通过，2：待审核，",description = "专栏状态:0：审核不通过。1：审核通过，2：待审核")
	@Transactional(rollbackFor = Exception.class, transactionManager = TransactionManagers.r2dbcTransactionManager)
	public Mono<Integer> changeState(@Param(value = "id") String id, @Param(value = "state")Integer state) {
		return snsColumnService
				.createUpdate()
				.set("status",state)
				.where("id",id)
				.execute()
				.defaultIfEmpty(1);

	}


}