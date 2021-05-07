package org.hswebframework.isdp.sdqysb.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hswebframework.isdp.organization.entity.UserDetailEntity;
import org.hswebframework.isdp.sdqysb.entity.CompUser;
import org.hswebframework.isdp.tenant.entity.TenantMemberDetail;
import org.hswebframework.web.system.authorization.api.entity.UserEntity;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompUserDetail {

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



    private String usccId ;
    private String compName ;
    private String compType ;
    private String compUserId ;
    private String recommendId ;
    private String compStatus ;


    public static CompUserDetail of(UserEntity entity) {
        return new CompUserDetail().with(entity);
    }


    public CompUserDetail with(CompUser entity) {
        entity.setId(entity.getId());
        entity.setCompName(entity.getCompName());
        entity.setCompType(entity.getCompType());
        entity.setCompUserId(entity.getId());
        entity.setRecommendId(entity.getRecommendId());
        entity.setCompStatus(entity.getCompStatus());
        return this;
    }

    public CompUserDetail with(UserEntity entity) {
        this.setId(entity.getId());
        this.setName(entity.getName());
        if (entity.getCreateTime() != null) {
            setCreateTime(entity.getCreateTime());
        }
        return this;
    }

}
