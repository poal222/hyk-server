package org.hswebframework.isdp.hyk.hospital.web;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.web.crud.service.ReactiveCrudService;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.hswebframework.web.crud.web.reactive.ReactiveServiceCrudController;
import org.hswebframework.isdp.hyk.hospital.entity.HospitalDuration;
import org.hswebframework.isdp.hyk.hospital.entity.HospitialEmployee;
import org.hswebframework.isdp.hyk.hospital.service.HospitalDurationService;
import org.hswebframework.isdp.hyk.hospital.service.HospitalEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/hyk/HospitialEmployee")
@Tag(name = "医馆-员工表")
public class HospitialEmployeeCmd implements ReactiveServiceCrudController<HospitialEmployee,String> {

	@Autowired
	private HospitalEmployeeService hospitalEmployeeService;



	@Override
	public ReactiveCrudService<HospitialEmployee, String> getService() {
		return hospitalEmployeeService;
	}

}