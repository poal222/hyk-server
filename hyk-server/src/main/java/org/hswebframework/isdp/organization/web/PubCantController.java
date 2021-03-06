package org.hswebframework.isdp.organization.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.hswebframework.isdp.organization.cant.entity.PubCant;
import org.hswebframework.isdp.organization.cant.service.PubCantService;
import org.hswebframework.web.api.crud.entity.*;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.annotation.QueryAction;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.service.ReactiveCrudService;
import org.hswebframework.web.crud.web.reactive.ReactiveServiceCrudController;
import org.hswebframework.web.system.authorization.api.entity.DimensionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/organ/cant/")
@Resource(id = "PubCant", name = "行政区划管理")
@Tag(name = "行政区划管理")
public class PubCantController implements ReactiveServiceCrudController<PubCant,String> {

	@Autowired
	private PubCantService pubCantService;
	@Override
	public ReactiveCrudService<PubCant, String> getService() {
		return pubCantService;
	}

	@GetMapping("/changeState")
	@Operation(  summary = "逻辑删除，更改状态，1：可用，0：不可用",description = "逻辑删除，更改状态，1：可用，0：不可用")
	@Transactional(rollbackFor = Exception.class, transactionManager = TransactionManagers.r2dbcTransactionManager)
	public Mono<Integer> changeState(@Param(value = "id") String id, @Param(value = "state")Integer state) {
		return pubCantService
				.createUpdate()
				.set("status",state)
				.where("id",id)
				.execute()
				.defaultIfEmpty(1);
	}
	@GetMapping("/_all/tree")
	@Authorize(merge = false)
	@Operation(summary = "获取全部行政区划信息(树结构)")
	public Flux<PubCant> getAllOrgTree(
			@Parameter(name = "superCode",description ="区划代码" )
			@Param(value = "superCode") String superCode) {
		return pubCantService.createQuery()
				.where("super_code",superCode)
				.fetch()
				.collectList()
				.flatMapIterable(list -> TreeSupportEntity.list2tree(list, PubCant::setChildren));
	}
}
