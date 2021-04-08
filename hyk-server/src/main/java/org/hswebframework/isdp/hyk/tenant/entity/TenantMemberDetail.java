package org.hswebframework.isdp.hyk.tenant.entity;

import lombok.Getter;
import lombok.Setter;
import org.hswebframework.isdp.hyk.tenant.enums.TenantDimensionType;
import org.hswebframework.isdp.hyk.tenant.enums.TenantMemberState;

@Getter
@Setter
public class TenantMemberDetail {

    private String tenantId;

    private String tenantName;

    private String tenantPhoto;

    private String userId;

    private TenantDimensionType type;

    private boolean adminMember;

    private boolean mainTenant;

    private TenantMemberState state;

    public static TenantMemberDetail of(TenantMemberEntity member, TenantEntity tenant) {
        TenantMemberDetail detail = new TenantMemberDetail();
        detail.setAdminMember(member.getAdminMember());
        detail.setMainTenant(member.getMainTenant());
        detail.setState(member.getState());
        detail.setTenantId(member.getTenantId());
        detail.setUserId(member.getUserId());
        detail.setType(member.getType());

        if (tenant != null) {
            detail.setTenantName(tenant.getName());
            detail.setTenantPhoto(tenant.getPhoto());
        }

        return detail;
    }
}
