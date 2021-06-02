package org.hswebframework.isdp.organization.vo;

import lombok.Getter;
import lombok.Setter;
import org.hswebframework.web.system.authorization.api.entity.DimensionUserEntity;

import java.util.List;

@Getter
@Setter
public class IsdpRoleUsers {

    private String roleId;
    private List<DimensionUserEntity> dimensionUserEntityList;
}
