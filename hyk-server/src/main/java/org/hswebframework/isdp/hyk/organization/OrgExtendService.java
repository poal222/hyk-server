package org.hswebframework.isdp.hyk.organization;

import org.hswebframework.isdp.hyk.organization.entity.OrganExtendEntity;
import org.hswebframework.web.crud.service.GenericReactiveCacheSupportCrudService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class OrgExtendService extends GenericReactiveCacheSupportCrudService<OrganExtendEntity,String> {

}
