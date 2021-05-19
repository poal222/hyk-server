package org.hswebframework.isdp.sdqysb.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.ezorm.rdb.mapping.annotation.DefaultValue;
import org.hswebframework.web.api.crud.entity.GenericEntity;
import org.hswebframework.web.crud.generator.Generators;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.JDBCType;

@Table(name = "comp_honor_info")
@Getter
@Setter
public class CompHonorInfo extends GenericEntity<String> {
    /**
     * 企业ID
     */
    @Schema(description = "企业ID")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "comp_id", length = 64)
    private String compId;
    /**
     * 所获荣誉
     */
    @Schema(description = "所获荣誉")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "horor", length = 64)
    private String horor;
    /**
     * 删除标识
     */
    @Schema(description = "删除标识")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "is_delete", length = 64)
    private String isDelete;
    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "create_user_id", length = 64)
    private String createUserId;
    /**
     * 最后一次更新时间
     */
    @Schema(description = "最后一次更新时间")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "last_update_time", length = 64)
    @DefaultValue(generator = Generators.CURRENT_TIME)
    private String lastUpdateTime;
    /**
     * 版本
     */
    @Schema(description = "版本")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "version", length = 64)
    private String version;
    /**
     * 发明专利
     */
    @Schema(description = "发明专利")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "invention", length = 64)
    private String invention ;
    /**
     * 使用新型
     */
    @Schema(description = "使用新型")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "utility", length = 64)
    private String utility ;

    /**
     * 企业特性
     */
    @Schema(description = "企业特性")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "tags")
    private String tags;

}