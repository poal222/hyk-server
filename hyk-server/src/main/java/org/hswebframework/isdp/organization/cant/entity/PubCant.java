package org.hswebframework.isdp.organization.cant.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.web.api.crud.entity.GenericTreeSortSupportEntity;

import javax.persistence.Column;
import javax.persistence.Index;
import javax.persistence.Table;
import java.sql.JDBCType;
import java.util.List;

/**
 * 行政区划表
 */
@Table(name = "pub_cant",  indexes = {
		@Index(name = "idx_cant_path", columnList = "path")
})
@Getter
@Setter
public class PubCant extends GenericTreeSortSupportEntity<String> {

	@Override
	public String getId() {
		return super.getId();
	}

    @Schema(description = "区划编码")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "CANT_CODE", length = 20)
    private String cantCode;

    @Schema(description = "区划名称")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "CANT_NAME", length = 250)
    private String cantName;


    @Schema(description = "区划简称")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "SHORT_NAME", length = 250)
    private String shortName;

    @Schema(description = "区划类型")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "CANT_TYPE", length = 10)
    private String cantType;


    @Schema(description = "上级行政区划")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "SUPER_CODE", length = 20)
    private String superCode;


    @Schema(description = "国家码")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "COUNTRY_CODE", length = 30)
    private String countryCode;

    @Schema(description = "备注，中国用CN")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "CANT_NOTE", length = 255)
    private String cantNote;

    @Schema(description = "是否启用：0：不启用，1：启用")
    @ColumnType(jdbcType = JDBCType.INTEGER)
    @Column(name = "status")
    private int status;


    @Schema(description = "子节点")
    private List<PubCant> children;


}
