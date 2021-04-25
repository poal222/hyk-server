package org.hswebframework.isdp.tenant.support;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.hswebframework.isdp.tenant.Tenant;
import org.hswebframework.isdp.tenant.TenantMember;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MultiTenantMember implements TenantMember {

    private final String userId;

    private final List<TenantMember> members;

    public TenantMember getMain() {
        for (TenantMember member : members) {
            if (member.isMain()) {
                return member;
            }
        }
        return members.get(0);
    }

    public List<TenantMember> getMembers() {
        return new ArrayList<>(members);
    }

    public List<Tenant> getTenants() {
        return members.stream()
            .map(TenantMember::getTenant)
            .collect(Collectors.toList());
    }

    @Override
    public @NonNull Tenant getTenant() {
        return getMain().getTenant();
    }

    @Override
    public @NonNull String getUserId() {
        return userId;
    }

    @Override
    public boolean isMain() {
        return false;
    }

    @Override
    public boolean isAdmin() {
        return getMain().isAdmin();
    }

}
