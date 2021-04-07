package org.hswebframework.isdp.hyk.hospital.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hswebframework.isdp.hyk.hospital.entity.HospitialEmployee;

import java.util.List;

@Data
public class HospitalVo {

	/** 医馆编码，使用信用代码 */
	private String code ;
	/** 医馆名称 */
	private String name ;
	/** 专业特长，医馆简介 */
	private String specialty ;
	/** 联系电话 */
	private String tel ;
	/** 地址 */
	private String address ;
	/** 封面图片 */
	private String imgPath ;
	/** 状态 */
	//	0:启用；1：待审核；2：无效
	private String status ;
	/** 医馆管理员账户 */
	@Schema(description = "用户 ID", type = "string", required = true,  example = "10086")
	private String userid ;

	private List<HospitialEmployee> hospitialEmployeeList ;

}
