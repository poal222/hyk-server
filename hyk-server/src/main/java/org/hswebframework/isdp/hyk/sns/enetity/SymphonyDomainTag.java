package org.hswebframework.isdp.hyk.sns.enetity;

import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 领域 - 标签关联表
 */
@Table(name="symphony_domain_tag")
public class SymphonyDomainTag extends GenericEntity<String> {

    /** 领域 id */
    private String domainOid ;
    /** 标签 id */
    private String tagOid ;


}