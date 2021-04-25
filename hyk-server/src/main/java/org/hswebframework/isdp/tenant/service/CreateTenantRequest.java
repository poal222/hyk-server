package org.hswebframework.isdp.tenant.service;

import lombok.Getter;
import lombok.Setter;
import org.hswebframework.isdp.tenant.entity.TenantEntity;
import org.hswebframework.isdp.tenant.enums.TenantState;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateTenantRequest {

    @NotBlank(message = "[name]不能为空")
    private String name;

    private String photo;

    private String type;

    private String description;

    @NotBlank(message = "[username]不能为空")
    private String username;

    @NotBlank(message = "[password]不能为空")
    private String password;

    public TenantEntity toTenantEntity() {
        TenantEntity entity = new TenantEntity();

        entity.setName(name);
        entity.setState(TenantState.enabled);
        entity.setDescription(description);
        entity.setPhoto(photo);

        return entity;


    }

}
