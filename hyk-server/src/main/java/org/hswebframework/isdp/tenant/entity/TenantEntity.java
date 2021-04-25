package org.hswebframework.isdp.tenant.entity;

import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.ezorm.rdb.mapping.annotation.DefaultValue;
import org.hswebframework.ezorm.rdb.mapping.annotation.EnumCodec;
import org.hswebframework.isdp.tenant.enums.TenantState;
import org.hswebframework.web.api.crud.entity.GenericEntity;
import org.hswebframework.web.crud.generator.Generators;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "s_tenant")
@Getter
@Setter
public class TenantEntity extends GenericEntity<String> {
    private static final long serialVersionUID = -7163490373702776998L;

    @Column(nullable = false)
    private String name;

    @Column
    private String type;

    @Column(length = 2000)
    private String photo;

    @Column
    private String description;

    @Column(nullable = false)
    @EnumCodec
    @ColumnType(javaType = String.class)
    @DefaultValue("enabled")
    private TenantState state;

    @Column(updatable = false)
    @DefaultValue(generator = Generators.CURRENT_TIME)
    private Date createTime;
}
