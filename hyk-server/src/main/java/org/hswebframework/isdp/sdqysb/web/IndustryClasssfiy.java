package org.hswebframework.isdp.sdqysb.web;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hswebframework.isdp.sdqysb.entity.IndustryClasssfiyEntity;
import org.hswebframework.isdp.sdqysb.service.IndustryClasssfiyService;
import org.hswebframework.web.api.crud.entity.QueryOperation;
import org.hswebframework.web.api.crud.entity.QueryParamEntity;
import org.hswebframework.web.api.crud.entity.TreeSupportEntity;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.annotation.QueryAction;
import org.hswebframework.web.authorization.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequestMapping("/industryClasssfiy")
@RestController
@Resource(id = "industryClasssfiy", name = "国民经济分类")
@Tag(name = "国民经济分类")
public class IndustryClasssfiy {


    @Autowired
    private IndustryClasssfiyService industryClasssfiyService;

    @GetMapping("/_all/tree")
    @QueryAction
    @Authorize(ignore = true)
    @QueryOperation(summary = "获取全部机构信息(树结构,不用权限)")
    public Flux<IndustryClasssfiyEntity> queryWithTenant(@Parameter(hidden = true) QueryParamEntity entity) {
        return industryClasssfiyService
                .createQuery()
                .fetch()
                .collectList()
                .flatMapIterable(list -> TreeSupportEntity.list2tree(list, IndustryClasssfiyEntity::setChildren));
    }
}
