package org.hswebframework.isdp.hyk.hospital.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.hswebframework.isdp.hyk.hospital.entity.HospitalBase;
import org.hswebframework.isdp.hyk.hospital.service.HospitalBaseService;
import org.hswebframework.isdp.hyk.hospital.service.HospitalEmployeeService;
import org.hswebframework.isdp.hyk.hospital.vo.HospitalVo;
import org.hswebframework.web.authorization.annotation.SaveAction;
import org.hswebframework.web.crud.service.ReactiveCrudService;
import org.hswebframework.web.crud.web.reactive.ReactiveServiceCrudController;
import org.hswebframework.web.id.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/hyk/hospitalBase")
@Tag(name = "医馆基本信息")
public class HospitalBaseController implements ReactiveServiceCrudController<HospitalBase,String> {

	@Autowired
	private HospitalBaseService hospitalBaseService;

	@Autowired
	private HospitalEmployeeService hospitalEmployeeService;

	@Override
	public ReactiveCrudService<HospitalBase, String> getService() {
		return hospitalBaseService;
	}


	/**
	 *保存 医馆信息，包含医师信息
	 * @param hospitalVo 医馆整体信息
	 * @return
	 */
	@PostMapping("/saveHospital")
	@SaveAction
	@Operation(summary = "保存 医馆信息，包含医师信息")

	public Mono<Integer> saveHospital(@RequestBody HospitalVo hospitalVo) {

		HospitalBase hospitalBase = new HospitalBase();
		hospitalBase.setCode(hospitalVo.getCode());
		hospitalBase.setName(hospitalVo.getName());
		hospitalBase.setTel(hospitalVo.getTel());
		hospitalBase.setUserid(hospitalVo.getUserid());
		hospitalBase.setAddress(hospitalVo.getAddress());
		hospitalBase.setStatus(hospitalVo.getStatus());
		hospitalBase.setSpecialty(hospitalVo.getSpecialty());
		hospitalBase.setImgPath(hospitalVo.getImgPath());
		hospitalBase.setId(IDGenerator.MD5.generate());

		Flux listFlux = Flux.fromIterable(hospitalVo.getHospitialEmployeeList())
				.flatMap(hospitialEmployee -> {
					hospitialEmployee.setHospitalId(hospitalBase.getId());
					return Mono.just(hospitialEmployee);
				});
		return Mono.zip(
				hospitalBaseService.insert(Mono.just(hospitalBase)),
				hospitalEmployeeService.insert(listFlux),
				(count, count1)->count);
	}
	//  查询状态是未审核的医馆信息

}
