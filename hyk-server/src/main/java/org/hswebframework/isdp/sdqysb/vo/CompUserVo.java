package org.hswebframework.isdp.sdqysb.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Date;
import java.sql.JDBCType;
@Getter
@Setter
public class CompUserVo implements Serializable {

    @Schema(description = "id")
    private String id ;
    /** uscc_id;统一社会信用代码, */
    @Schema(description = "统一社会信用代码")
    private String usccId ;
    /** comp_name;企业名称 */
    @Schema(description = "企业名称")
    private String compName ;
    /** comp_type;企业类型 */
    @Schema(description = "企业类型")
    private String compType ;
    /** comp_user_id;关联的用户id */
    @Schema(description = "关联的用户id")
    private String compUserId ;

    /** comp_user_id;关联的用户id */
    @Schema(description = "关联的用户名")
    private String compUserName ;
    /** comp_user_id;关联的用户id */
    @Schema(description = "关联的用户类型")
    private String compUserType ;

    /** recommend_id;推荐单位，也是审核单位 */
    @Column(name = "recommend_id",length = 250)
    private String recommendId ;
    /** comp_status;企业状态 */
    @Schema(description = "企业状态")
    private String compStatus ;
    /** last_update_time;最后一次修改时间 */
    @Schema(description = "最后一次修改时间")
    private Date lastUpdateTime ;
}
