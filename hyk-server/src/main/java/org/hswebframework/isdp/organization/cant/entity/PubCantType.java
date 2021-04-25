package org.hswebframework.isdp.organization.cant.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.JDBCType;

/**
 * 区划类型表
 */
@Table(name = "pub_cant_type")
@Getter
@Setter
public class PubCantType extends GenericEntity<String> {

	@Schema(description = "区划类型编码")
	@ColumnType(jdbcType = JDBCType.VARCHAR)
	@Column(name = "CANT_TYPE",length = 10)
	private String cantType;

	@Schema(description = "区划类型名称")
	@ColumnType(jdbcType = JDBCType.VARCHAR)
	@Column(name = "TYPE_NAME",length = 60)
	private String typeName;

	@Schema(description = "是否启用：0：不启用，1：启用")
	@ColumnType(jdbcType = JDBCType.INTEGER)
	@Column(name = "status")
	private int status;

}
