package org.hswebframework.isdp.hyk.hospital.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.hswebframework.isdp.hyk.hospital.entity.HospitalAppiontment;
import org.hswebframework.isdp.hyk.hospital.service.HospitalAppiontmentService;
import org.hswebframework.isdp.hyk.hospital.service.HospitalDurationService;
import org.hswebframework.web.authorization.annotation.SaveAction;
import org.hswebframework.web.crud.service.ReactiveCrudService;
import org.hswebframework.web.crud.web.reactive.ReactiveServiceCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/hyk/HospitalAppiontment")
@Tag(name = "医馆坐诊")
public class HospitalAppiontmentController implements ReactiveServiceCrudController<HospitalAppiontment, String> {

	@Autowired
	private HospitalAppiontmentService hospitalAppiontmentService;

	@Autowired
	private HospitalDurationService hospitalDurationService;



//	@Transactional(rollbackFor = Exception.class)
//	@PostMapping("/hyk/HospitalAppiontment")
//	@Operation(summary = "企业信息总提交")
//	public Mono<Integer> insertFinl(@RequestBody HBKPO hbkpo) {
//
//
//		return Mono.zip(
//				hospitalAppiontmentService.insert(hbkpo.getHospitalAppiontment()),
//				hospitalDurationService.insert(hbkpo.getHospitalBase()).then(Mono.just(12))
//		);
////		return hospitalAppiontmentService.insert(hospitalAppiontment)
////				.zipWith()
//
//	}


	/**
	 *
	 * @param doctorId 医生id
	 * @param date 查询的预约日期
	 * @param type 时间段类型
	 * @return
	 */
	@GetMapping("/leftAppiontmentNums")
	@SaveAction
	@Operation(summary = "查询某个时间段剩余就诊数")

	public Mono<Integer> leftAppiontmentNums(@Parameter(description = "医生id") @RequestParam (value = "doctorId") String doctorId,
			@Parameter(description = "查询的预约日期") @RequestParam (value = "date") String date, @Parameter(description = "上午还是下午") @RequestParam(value = "type") String type) {



		return hospitalDurationService.createQuery().where("DOCTOR_ID", doctorId).and("DATE", date).and("TYPE", type)
				.fetchOne().flatMap(hospitalDuration -> {
					return getService().createQuery().where("DOCTOR_ID", doctorId).and("DATE", date)
							.and("TYPE", type).count().flatMap(value -> {
								return Mono.just(value - Integer.parseInt(hospitalDuration.getPlanNums()));
							});
				});

	}

	@Override
	public ReactiveCrudService<HospitalAppiontment, String> getService() {
		return hospitalAppiontmentService;
	}
}
