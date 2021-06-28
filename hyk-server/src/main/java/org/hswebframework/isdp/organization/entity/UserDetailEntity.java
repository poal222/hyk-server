package org.hswebframework.isdp.organization.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 用户信息扩展表
 */
@Table(name = "s_user_detail")
@Getter
@Setter
public class UserDetailEntity extends GenericEntity<String> {

    @Column(nullable = false)
    @NotBlank(message = "姓名不能为空")
    private String name;

    @Column
    @Email(message = "邮件格式错误")
    private String email;

    @Column(length = 32)
    private String telephone;

    @Column(length = 2000)
    @URL(message = "头像格式错误")
    private String avatar;

    @Column(length = 2000)
    private String description;


	/**
	 * 企业级开发平台拓展信息 主岗信息
	 */
	@Column(nullable = false,length = 64)
//	@NotBlank(message = "所属公司id")
	private String coId;
	@Column(nullable = false,length = 64)
//	@NotBlank(message = "所属部门id")
	private String departmentId;
	@Column(nullable = false,length = 64)
//	@NotBlank(message = "所属科室/岗位id")
	private String officesId;
}
