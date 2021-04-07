package org.hswebframework.isdp.hyk.hospital.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.ColumnType;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.JDBCType;
import java.util.Date;


@Table(name="hospital_base")
@Getter
@Setter
@Schema(description = "医院基本信息")
public class HospitalBase extends GenericEntity<String> {
	/** 乐观锁 */
	private Integer revision ;
	/** 创建人 */
	private String createdBy ;
	/** 创建时间 */
	private Date createdTime ;
	/** 更新人 */
	private String updatedBy ;
	/** 更新时间 */
	private Date updatedTime ;

	/** 医馆编码，使用信用代码 */
	@Schema(description = "医馆编码")
	@Column(length = 60)
	@ColumnType(javaType = String.class)
	private String code ;
	/** 医馆名称 */
	@Schema(description = "医馆名称")
	@Column(length = 60)
	@ColumnType(javaType = String.class)
	private String name ;
	/** 专业特长，医馆简介 */
	@Schema(description = "医馆简介")
	@Column
	@ColumnType(jdbcType = JDBCType.LONGVARCHAR)
	private String specialty ;
	/** 联系电话 */
	@Schema(description = "联系电话")
	@Column(length = 25)
	@ColumnType(javaType = String.class)
	private String tel ;
	/** 地址 */
	@Schema(description = "地址")
	@Column
	@ColumnType(jdbcType = JDBCType.LONGVARCHAR)
	private String address ;
	/** 封面图片 */
	@Schema(description = "封面图片")
	@Column
	@ColumnType(javaType = String.class)
	private String imgPath ;
	/** 状态 */
	@Schema(description = "状态")
	@Column
	@ColumnType(javaType = String.class)
//	0:启用；1：待审核；2：无效
	private String status ;
	/** 医馆管理员账户 */

	@Schema(description = "用户 ID", type = "string", required = true,  example = "10086")
	@Column
	@ColumnType(javaType = String.class)
	private String userid ;

//	/** 乐观锁 */
//	public Integer getRevision(){
//		return this.revision;
//	}
//	/** 乐观锁 */
//	public void setRevision(Integer revision){
//		this.revision = revision;
//	}
//	/** 创建人 */
//	public String getCreatedBy(){
//		return this.createdBy;
//	}
//	/** 创建人 */
//	public void setCreatedBy(String createdBy){
//		this.createdBy = createdBy;
//	}
//	/** 创建时间 */
//	public Date getCreatedTime(){
//		return this.createdTime;
//	}
//	/** 创建时间 */
//	public void setCreatedTime(Date createdTime){
//		this.createdTime = createdTime;
//	}
//	/** 更新人 */
//	public String getUpdatedBy(){
//		return this.updatedBy;
//	}
//	/** 更新人 */
//	public void setUpdatedBy(String updatedBy){
//		this.updatedBy = updatedBy;
//	}
//	/** 更新时间 */
//	public Date getUpdatedTime(){
//		return this.updatedTime;
//	}
//	/** 更新时间 */
//	public void setUpdatedTime(Date updatedTime){
//		this.updatedTime = updatedTime;
//	}
//	/** id */
//	public String getId(){
//		return this.id;
//	}
//	/** id */
//	public void setId(String id){
//		this.id = id;
//	}
//	/** 医馆编码，使用信用代码 */
//	public String getCode(){
//		return this.code;
//	}
//	/** 医馆编码，使用信用代码 */
//	public void setCode(String code){
//		this.code = code;
//	}
//	/** 医馆名称 */
//	public String getName(){
//		return this.name;
//	}
//	/** 医馆名称 */
//	public void setName(String name){
//		this.name = name;
//	}
//	/** 专业特长，医馆简介 */
//	public String getSpecialty(){
//		return this.specialty;
//	}
//	/** 专业特长，医馆简介 */
//	public void setSpecialty(String specialty){
//		this.specialty = specialty;
//	}
//	/** 联系电话 */
//	public String getTel(){
//		return this.tel;
//	}
//	/** 联系电话 */
//	public void setTel(String tel){
//		this.tel = tel;
//	}
//	/** 地址 */
//	public String getAddress(){
//		return this.address;
//	}
//	/** 地址 */
//	public void setAddress(String address){
//		this.address = address;
//	}
//	/** 封面图片 */
//	public String getImgPath(){
//		return this.imgPath;
//	}
//	/** 封面图片 */
//	public void setImgPath(String imgPath){
//		this.imgPath = imgPath;
//	}
}