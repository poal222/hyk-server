package org.hswebframework.isdp.hyk.tenant.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hswebframework.isdp.hyk.tenant.entity.TenantEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TenantDetail {

    private TenantEntity tenant;

    private int members;


    public static TenantDetail of(TenantEntity tenant) {
        return new TenantDetail(tenant, 0);
    }

}
