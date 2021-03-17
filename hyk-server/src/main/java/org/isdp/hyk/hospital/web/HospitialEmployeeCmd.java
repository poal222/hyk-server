package org.isdp.hyk.hospital.web;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.isdp.hyk.hospital.entity.HospitialEmployee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/hyk/HospitialEmployee")
@Tag(name = "医馆-员工表")
public class HospitialEmployeeCmd implements ReactiveCrudController<HospitialEmployee,String> {

	private final ReactiveRepository<HospitialEmployee, String> repository;

	@Override
	public ReactiveRepository<HospitialEmployee, String> getRepository() {
		return repository;
	}


}