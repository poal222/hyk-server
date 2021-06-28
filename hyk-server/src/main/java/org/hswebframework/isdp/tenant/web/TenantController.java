package org.hswebframework.isdp.tenant.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.hswebframework.isdp.tenant.entity.TenantEntity;
import org.hswebframework.isdp.tenant.entity.TenantMemberEntity;
import org.hswebframework.isdp.tenant.service.*;
import org.hswebframework.isdp.tenant.vo.TenantDetail;
import org.hswebframework.web.api.crud.entity.PagerResult;
import org.hswebframework.web.api.crud.entity.QueryParamEntity;
import org.hswebframework.web.authorization.annotation.QueryAction;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.authorization.annotation.SaveAction;
import org.hswebframework.web.crud.service.ReactiveCrudService;
import org.hswebframework.web.crud.web.reactive.ReactiveServiceQueryController;
import org.hswebframework.web.crud.web.reactive.ReactiveServiceSaveController;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

/**
 * 管理端,租户管理
 * Tenants 租户
 * 是一个独立的商业实体：比如个人或组织，可以拥有若产生多个设备或资产。一个租户可有多个租户管理员用户及多个客户。
 *
 * 可将它理解为代理商，中间商，如中国移动是一个 tenant, 承包了某地区的所有项目，信息保存在 tenant 表中。
 * @author zhouhao
 * @since 1.3
 */
@RestController
@RequestMapping("/tenant")
@Resource(id = "tenant-manager", name = "租户管理-管理端")
@Tag(name = "租户管理-管理端")
public class TenantController implements
    ReactiveServiceQueryController<TenantEntity, String>,
    ReactiveServiceSaveController<TenantEntity, String> {

    private final TenantService tenantService;

    private final TenantMemberService memberService;


    public TenantController(TenantService tenantService,
                            TenantMemberService memberService
                            ) {
        this.tenantService = tenantService;
        this.memberService = memberService;
    }

    @Override
    public ReactiveCrudService<TenantEntity, String> getService() {
        return tenantService;
    }

    /**
     * 创建租户
     *
     * @param requestMono 请求
     * @return 不报错就成功
     */
    @PostMapping("/_create")
    @SaveAction
    public Mono<TenantEntity> getTenantMembers(@RequestBody Mono<CreateTenantRequest> requestMono) {
        return requestMono.flatMap(tenantService::createTenant);
    }

    /**
     * 分页查询租户详细列表
     *
     * @param query 查询条件
     * @return 分页结果
     */
    @GetMapping("/detail/_query")
    @QueryAction
    public Mono<PagerResult<TenantDetail>> queryTenantDetail(QueryParamEntity query) {
        return tenantService.queryTenantDetail(query);
    }

    /**
     * 查询成员信息
     *
     * @param tenantId 租户ID
     * @param query    查询条件
     * @return 查询结果
     */
    @GetMapping("/{tenantId}/members/_query")
    @QueryAction
    public Mono<PagerResult<TenantMemberEntity>> getTenantMembers(@PathVariable String tenantId,
                                                                  QueryParamEntity query) {
        return query.toQuery()
            .and(TenantMemberEntity::getTenantId, tenantId)
            .execute(memberService::queryPager);
    }



    /**
     * 给指定租户创建成员信息
     *
     * @param tenantId 租户ID
     * @param request  请求
     * @return 结果
     */
    @PostMapping("/{tenantId}/member")
    @SaveAction
    public Mono<TenantMemberEntity> createMember(@PathVariable String tenantId,
                                                 @RequestBody Mono<CreateMemberRequest> request) {
        return request.flatMap(createRequest -> memberService.createMember(tenantId, createRequest));
    }

    /**
     * 绑定成员
     *
     * @param tenantId 租户ID
     * @param request  请求
     * @return empty Mono
     */
    @PostMapping("/{tenantId}/members/_bind")
    @SaveAction
    public Mono<Void> bindMember(@PathVariable String tenantId,
                                 @RequestBody Flux<BindMemberRequest> request) {
        return memberService.bindMembers(tenantId, request);
    }

    /**
     * 解绑成员
     *
     * @param tenantId 租户ID
     * @param request  bindId
     * @return empty Mono
     */
    @PostMapping("/{tenantId}/members/_unbind")
    @SaveAction
    public Mono<Void> bindMember(@PathVariable String tenantId,
                                 @RequestBody Mono<List<String>> request) {
        return memberService.unbindMembers(tenantId, request.flatMapIterable(Function.identity()));
    }


}
