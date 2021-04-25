package org.hswebframework.isdp.tenant.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hswebframework.isdp.tenant.Tenant;

import java.io.Serializable;

/**
 * 默认租户信息
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DefaultTenant implements Tenant, Serializable {
    private static final long serialVersionUID = -6849794470754667710L;
    /**
     * 租户id
     */
    private String id;
    /**
     * 租户姓名
     */
    private String name;


}
