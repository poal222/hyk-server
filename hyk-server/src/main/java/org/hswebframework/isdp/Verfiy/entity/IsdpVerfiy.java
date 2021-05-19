package org.hswebframework.isdp.Verfiy.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.ezorm.rdb.mapping.annotation.EnumCodec;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.JDBCType;

@Getter
@Setter
@Table(name = "pub_verfiy")
public class IsdpVerfiy extends GenericEntity<String> {

    /**
     * 身份证
     */
    @Schema(description = "认证地区")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "origin", length = 64)
    private String origin;
    /**
     * 身份证
     */
    @Schema(description = "身份证")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "id_number", length = 64)
    private String idNumber;
    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column
    private String RealName;
    /**
     * 认证类型
     */
    @Schema(description = "认证类型")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column
    @EnumCodec
    private VerfiyEmnu type;
    /**
     * 证件正面照片
     */
    @Schema(description = "证件正面照片")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column
    private String frontImg;
    /**
     * 证件背面照片
     */
    @Schema(description = "证件背面照片")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column
    private String backImg;
    /**
     * 用户id
     */
    @Schema(description = "用户id")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column
    private String userId;
}
