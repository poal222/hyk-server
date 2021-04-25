package org.hswebframework.isdp.tenant.service;

import lombok.*;
import org.hswebframework.isdp.tenant.entity.TenantMemberEntity;
import org.hswebframework.isdp.tenant.enums.TenantDimensionType;
import org.hswebframework.isdp.tenant.enums.TenantMemberState;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BindMemberRequest {

    @NotBlank(message = "[name]不能为空")
    private String name;

    @NotBlank(message = "[userId]不能为空")
    private String userId;

    private String description;

    private boolean admin;

    public TenantMemberEntity toMember() {
        TenantMemberEntity entity = new TenantMemberEntity();
        entity.setAdminMember(isAdmin());
        entity.setCreateTime(new Date());
        entity.setName(name);
        entity.setDescription(getDescription());
        entity.setUserId(userId);
        entity.setState(TenantMemberState.enabled);
        entity.setType(TenantDimensionType.tenantMember);
        return entity;
    }
}
