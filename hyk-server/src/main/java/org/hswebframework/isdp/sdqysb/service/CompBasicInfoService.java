package org.hswebframework.isdp.sdqysb.service;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.isdp.sdqysb.entity.CompBasicInfo;
import org.hswebframework.isdp.sdqysb.entity.CompUser;
import org.hswebframework.web.api.crud.entity.GenericEntity;
import org.hswebframework.web.crud.service.GenericReactiveCacheSupportCrudService;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.JDBCType;

@Service
public class CompBasicInfoService extends GenericReactiveCacheSupportCrudService<CompBasicInfo,String> {


}
