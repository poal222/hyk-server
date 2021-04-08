package org.hswebframework.isdp.hyk.tenant.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.ezorm.rdb.mapping.annotation.DefaultValue;
import org.hswebframework.ezorm.rdb.mapping.annotation.EnumCodec;
import org.hswebframework.ezorm.rdb.mapping.annotation.JsonCodec;
import org.hswebframework.isdp.hyk.tenant.enums.TenantDimensionType;
import org.hswebframework.isdp.hyk.tenant.enums.TenantMemberState;
import org.hswebframework.web.api.crud.entity.GenericEntity;
import org.hswebframework.web.crud.generator.Generators;

import javax.persistence.Column;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Table(name = "s_tenant_member", indexes = {
    @Index(name = "idx_tenant_tenant_id", columnList = "tenant_id"),
    @Index(name = "idx_tenant_user_id", columnList = "user_id")
})
@Getter
@Setter
public class TenantMemberEntity extends GenericEntity<String> {

    @Column(nullable = false, length = 64, updatable = false)
    private String tenantId;

    @Column(nullable = false, length = 64, updatable = false)
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @EnumCodec
    @ColumnType(javaType = String.class)
    @DefaultValue("tenantMember")
    private TenantDimensionType type;

    @Column
    private String description;

    @Column
    @DefaultValue("false")
    private Boolean adminMember;

    @Column
    @DefaultValue("true")
    private Boolean mainTenant;

    @Column(updatable = false)
    @DefaultValue(generator = Generators.CURRENT_TIME)
    private Date createTime;

    @Column
    @EnumCodec
    @ColumnType(javaType = String.class)
    @DefaultValue("enabled")
    private TenantMemberState state;

    public String generateId() {
        this.setId(DigestUtils.md5Hex(String.format("%s|%s", tenantId, userId)));
        return getId();
    }
}
