package org.isdp.hyk.hospital.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.isdp.hyk.hospital.entity.HospitalBase;
import org.isdp.hyk.hospital.entity.HospitalDuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/hyk/HospitalDuration")
@Tag(name = "医馆坐诊")
public class HospitalDurationController implements ReactiveCrudController<HospitalDuration,String> {

   private final ReactiveRepository<HospitalDuration, String> repository;

    @Override
    public ReactiveRepository<HospitalDuration, String> getRepository() {
        return repository;
    }


}
