package org.isdp.hyk.hospital.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.isdp.hyk.hospital.entity.HospitalBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/hyk/hospitalBase")
@Tag(name = "医馆基本信息")
public class HospitalBaseController implements ReactiveCrudController<HospitalBase,String> {

   private final ReactiveRepository<HospitalBase, String> repository;

    @Override
    public ReactiveRepository<HospitalBase, String> getRepository() {
        return repository;
    }


}
