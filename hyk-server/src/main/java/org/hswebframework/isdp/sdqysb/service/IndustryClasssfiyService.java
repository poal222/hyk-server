package org.hswebframework.isdp.sdqysb.service;

import org.hswebframework.isdp.sdqysb.entity.IndustryClasssfiyEntity;
import org.hswebframework.web.crud.service.GenericReactiveTreeSupportCrudService;
import org.hswebframework.web.id.IDGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndustryClasssfiyService extends
        GenericReactiveTreeSupportCrudService<IndustryClasssfiyEntity, String> {


    @Override
    public IDGenerator<String> getIDGenerator() {
        return IDGenerator.MD5;
    }

    @Override
    public void setChildren(IndustryClasssfiyEntity entity, List<IndustryClasssfiyEntity> children) {
        entity.setChildren(children);
    }
}