package org.hswebframework.isdp.organization.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.hswebframework.isdp.organization.cant.entity.PubCantType;
import org.hswebframework.isdp.organization.cant.service.PubCantTypeService;
import org.hswebframework.web.api.crud.entity.TransactionManagers;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.service.ReactiveCrudService;
import org.hswebframework.web.crud.web.reactive.ReactiveServiceCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/organ/cant/type")
@Tag(name = "行政区划类型")
@Resource(id = "PubCanttype", name = "行政区划类型")
public class PubCantTypeController  implements ReactiveServiceCrudController<PubCantType,String> {

   @Autowired
   private PubCantTypeService pubCantTypeService;


	@Override
	public ReactiveCrudService<PubCantType, String> getService() {
		return pubCantTypeService;
	}

	@GetMapping("/changeState")
	@Operation(  summary = "逻辑删除，更改状态，1：可用，0：不可用",description = "逻辑删除，更改状态，1：可用，0：不可用")
	@Transactional(rollbackFor = Exception.class, transactionManager = TransactionManagers.r2dbcTransactionManager)
	public Mono<Integer> changeState(@Param(value = "id") String id, @Param(value = "state")Integer state) {
		return pubCantTypeService
				.createUpdate()
				.set("status",state)
				.where("id",id)
				.execute()
				.defaultIfEmpty(1);

	}
}
