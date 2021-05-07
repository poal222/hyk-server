package org.hswebframework.isdp.sdqysb.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.ezorm.rdb.mapping.annotation.DefaultValue;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.JDBCType;

/**
 * 企业用户详情表：企业用户自行注册时，后台会看到该表记录信息
 */
@Table(name = "comp_main")
@Getter
@Setter
public class CompUser  extends GenericEntity<String> {
    /** id;id */
    @Schema(description = "id")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "id",length = 64)
    private String id ;
    /** uscc_id;统一社会信用代码, */
    @Schema(description = "统一社会信用代码")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "uscc_id",length = 150)
    private String usccId ;
    /** comp_name;企业名称 */
    @Schema(description = "企业名称")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "comp_name",length = 250)
    private String compName ;
    /** comp_type;企业类型 */
    @Schema(description = "企业类型")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "comp_type",length = 10)
    private String compType ;
    /** comp_user_id;关联的用户id */
    @Schema(description = "关联的用户id")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "comp_user_id",length = 64)
    private String compUserId ;
    /** recommend_id;推荐单位，也是审核单位 */
    @Schema(description = "推荐单位，也是审核单位 ")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "recommend_id",length = 250)
    private String recommendId ;
    /** comp_status;企业状态 */
    @Schema(description = "企业状态 0：待审核，1：审核通过，2：审核不通过")
    @ColumnType(jdbcType = JDBCType.VARCHAR)
    @Column(name = "comp_status",length = 250)
    private String compStatus ;
    /** last_update_time;最后一次修改时间 */
    @Schema(description = "最后一次修改时间")
    @ColumnType(jdbcType = JDBCType.DATE)
    @Column(name = "last_update_time")
    @DefaultValue(generator = "current_time")
    private Date lastUpdateTime ;



}
