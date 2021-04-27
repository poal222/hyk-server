package org.hswebframework.isdp.organization.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.collections4.CollectionUtils;
import org.hswebframework.isdp.organization.OrgDimensionType;
import org.hswebframework.isdp.organization.organ.entity.OrganExtendEntity;
import org.hswebframework.isdp.organization.organ.service.OrgExtendService;
import org.hswebframework.isdp.organization.vo.IsdpOrganVo;
import org.hswebframework.web.api.crud.entity.PagerResult;
import org.hswebframework.web.api.crud.entity.QueryOperation;
import org.hswebframework.web.api.crud.entity.QueryParamEntity;
import org.hswebframework.web.api.crud.entity.TreeSupportEntity;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.Dimension;
import org.hswebframework.web.authorization.annotation.*;
import org.hswebframework.web.system.authorization.api.entity.DimensionEntity;
import org.hswebframework.web.system.authorization.defaults.service.DefaultDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/organization")
@RestController
@Resource(id = "organization", name = "机构管理")
@Tag(name = "机构管理")
public class OrganizationController {

    static String orgDimensionTypeId = OrgDimensionType.org.getId();

    @Autowired
    private DefaultDimensionService dimensionService;
    @Autowired
    private OrgExtendService orgExtendService;

    @GetMapping("/_all/tree")
    @Authorize(merge = false)
    @Operation(summary = "获取全部机构信息(树结构)")
    public Flux<DimensionEntity> getAllOrgTree() {
        return getAllOrg()
            .collectList()
            .flatMapIterable(list -> TreeSupportEntity.list2tree(list, DimensionEntity::setChildren));
    }

    @GetMapping("/_all")
    @Authorize(merge = false)
    @Operation(summary = "获取全部机构信息")
    public Flux<DimensionEntity> getAllOrg() {
        return Authentication
            .currentReactive()
            .flatMapMany(auth -> {
                List<String> list = auth.getDimensions(orgDimensionTypeId)
                    .stream()
                    .map(Dimension::getId)
                    .collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(list)) {
                    return dimensionService.findById(list);
                }
                return dimensionService
                    .createQuery()
                    .where(DimensionEntity::getTypeId, orgDimensionTypeId)
                    .fetch();
            });
    }

    @GetMapping("/_query")
    @QueryAction
    @QueryOperation(summary = "查询结构列表")
    public Mono<PagerResult<DimensionEntity>> queryDimension(@Parameter(hidden = true) QueryParamEntity entity) {
        return entity
            .toNestQuery(q -> q.where(DimensionEntity::getTypeId, orgDimensionTypeId))
            .execute(Mono::just)
            .as(dimensionService::queryPager);
    }
//    @GetMapping("/_queryWithTenant")
//    @QueryAction
//    @QueryOperation(summary = "查询结构列表")
//    public Mono<PagerResult<DimensionEntity>> queryWithTenant(@Parameter(hidden = true) QueryParamEntity entity) {
//        return entity
//                .toNestQuery(q -> q.where(DimensionEntity::getTypeId, orgDimensionTypeId))
//                .execute(Mono::just)
//                .as(dimensionService::queryPager);
//    }
//    @PatchMapping
//    @SaveAction
//    @Operation(summary = "保存机构信息")
//    public Mono<Void> saveOrg(@RequestBody Flux<DimensionEntity> entityFlux) {
//        return entityFlux
//            .doOnNext(entity -> entity.setTypeId(orgDimensionTypeId))
//            .as(dimensionService::save)
//            .then();
//    }
    @PatchMapping
    @SaveAction
    @Operation(summary = "保存机构信息")
    public Mono<Integer> saveOrg(@RequestBody IsdpOrganVo isdpOrganVo) {
        DimensionEntity dimensionEntity=  isdpOrganVo.getDimensionEntity();
        dimensionEntity.setTypeId(orgDimensionTypeId);
        OrganExtendEntity organExtendEntity = isdpOrganVo.getOrganExtendEntity();
        return Mono.zip(
                dimensionService.save(Mono.just(dimensionEntity)),
                orgExtendService.save(Mono.just(organExtendEntity)),
                (count, count1)->1
        );
    }

    @PatchMapping({"/saveOrgWithTenant"})
    @SaveAction
    @Operation(summary = "保存机构信息,saas模式下，带租户id")
    public Mono<Void> saveOrgWithTenant(@RequestBody Flux<IsdpOrganVo> entityFlux) {
       Flux item1= entityFlux.map((isdpOrganVo -> isdpOrganVo.getDimensionEntity()));
        Flux item2= entityFlux.map((isdpOrganVo -> isdpOrganVo.getOrganExtendEntity()));


        return Mono.zip(
                dimensionService.insert(item1),
                orgExtendService.insert(item2),
                        (count, count1)->count);
    }
    @DeleteMapping("/{id}")
    @DeleteAction
    @Operation(summary = "删除机构信息")
    public Mono<Void> deleteOrg(@PathVariable String id) {
        return dimensionService
            .deleteById(Mono.just(id))
            .then();
    }


}
