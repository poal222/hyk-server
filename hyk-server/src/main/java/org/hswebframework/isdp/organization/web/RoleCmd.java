package org.hswebframework.isdp.organization.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hswebframework.isdp.organization.vo.IsdpRoleUsers;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.authorization.annotation.SaveAction;
import org.hswebframework.web.system.authorization.api.entity.DimensionUserEntity;
import org.hswebframework.web.system.authorization.defaults.service.DefaultDimensionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/role-user")
@Authorize
@Resource(id = "role_user", name = "权限维度用户关联管理", group = "system")
@Tag(name = "权限维度用户关联管理")
public class RoleCmd {

    @Autowired
    private DefaultDimensionUserService dimensionUserService;

    @PatchMapping
    @SaveAction
    @Operation(summary = "保存数据", description = "如果传入了id,并且对应数据存在,则尝试覆盖,不存在则新增.")
    public Mono<Integer> save(@RequestBody IsdpRoleUsers payload) {
        return dimensionUserService.createDelete().where(DimensionUserEntity::getDimensionId, payload.getRoleId())
                .execute()
                .then(dimensionUserService.insertBatch(Flux.just(payload.getDimensionUserEntityList())));
    }
}
