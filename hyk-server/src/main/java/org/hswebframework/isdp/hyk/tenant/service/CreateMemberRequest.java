package org.hswebframework.isdp.hyk.tenant.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.isdp.hyk.tenant.entity.TenantMemberEntity;
import org.hswebframework.isdp.hyk.tenant.enums.TenantDimensionType;
import org.hswebframework.isdp.hyk.tenant.enums.TenantMemberState;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateMemberRequest {

    @NotBlank(message = "[name]不能为空")
    private String name;

    @NotBlank(message = "[username]不能为空")
    private String username;

    @NotBlank(message = "[password]不能为空")
    private String password;

    private String description;

    private boolean admin;

    public TenantMemberEntity toMember() {
        TenantMemberEntity entity = new TenantMemberEntity();
        entity.setAdminMember(isAdmin());
        entity.setCreateTime(new Date());
        entity.setName(name);
        entity.setDescription(getDescription());
        entity.setState(TenantMemberState.enabled);
        entity.setType(TenantDimensionType.tenantMember);
        return entity;
    }
}
