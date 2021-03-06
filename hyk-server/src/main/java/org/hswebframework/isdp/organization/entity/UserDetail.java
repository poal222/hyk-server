package org.hswebframework.isdp.organization.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hswebframework.isdp.tenant.entity.TenantMemberDetail;
import org.hswebframework.web.system.authorization.api.entity.UserEntity;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDetail {

    @Schema(description = "用户ID")
    private String id;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "email")
    private String email;

    @Schema(description = "联系电话")
    private String telephone;

    @Schema(description = "头像图片地址")
    private String avatar;

    @Schema(description = "说明")
    private String description;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "创建时间")
    private long createTime;

    @Schema(description = "租户信息")
    private List<TenantMemberDetail> tenants;


    /**
     * 企业级开发平台拓展信息 主岗信息
     */
    @NotBlank(message = "所属公司id")
    private String coId;
    //    @NotBlank(message = "所属部门id")
    private String departmentId;
    //    @NotBlank(message = "所属科室/岗位id")
    private String officesId;


    private boolean tenantDisabled;

    public static UserDetail of(UserEntity entity) {
        return new UserDetail().with(entity);
    }


    public UserDetail with(UserDetailEntity entity) {
        this.setAvatar(entity.getAvatar());
        this.setDescription(entity.getDescription());
        this.setTelephone(entity.getTelephone());
        this.setEmail(entity.getEmail());
        this.setCoId(entity.getCoId());
        this.setDepartmentId(entity.getDepartmentId());
        this.setOfficesId(entity.getOfficesId());
        this.setDescription(entity.getDescription());
        return this;
    }

    public UserDetail with(UserEntity entity) {
        this.setId(entity.getId());
        this.setName(entity.getName());
        if (entity.getCreateTime() != null) {
            setCreateTime(entity.getCreateTime());
        }
        return this;
    }

}
