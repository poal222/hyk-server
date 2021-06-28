package org.hswebframework.isdp.hyk.hospital.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.hswebframework.isdp.hyk.hospital.entity.HospitalDuration;
import org.hswebframework.isdp.hyk.hospital.service.HospitalDurationService;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.service.ReactiveCrudService;
import org.hswebframework.web.crud.web.reactive.ReactiveServiceCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/hyk/HospitalDuration")
@Tag(name = "医馆坐诊")
@Resource(id = "HospitalDuration", name = "医馆坐诊")
public class HospitalDurationController implements ReactiveServiceCrudController<HospitalDuration,String> {

	@Autowired
	private HospitalDurationService hospitalDurationService;



	@Override
	public ReactiveCrudService<HospitalDuration, String> getService() {
		return hospitalDurationService;
	}

}
