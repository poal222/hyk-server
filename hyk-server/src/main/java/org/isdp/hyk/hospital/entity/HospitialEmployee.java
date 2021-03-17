package org.isdp.hyk.hospital.entity;

import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Table;
import java.util.Date;

@Table(name="hospitial_employee")
public class HospitialEmployee extends GenericEntity<String> {
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
	/** 医馆ID */
	private String hospitalId ;
	/** 员工编号 */
	private String code ;
	/** 员工姓名 */
	private String name ;
	/** 类型 */
	private String type ;
	/** 状态;0：在职，1：离职 */
	private String status ;
	/** 专长 */
	private String desc ;

	/** 乐观锁 */
	public Integer getRevision(){
		return this.revision;
	}
	/** 乐观锁 */
	public void setRevision(Integer revision){
		this.revision = revision;
	}
	/** 创建人 */
	public String getCreatedBy(){
		return this.createdBy;
	}
	/** 创建人 */
	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}
	/** 创建时间 */
	public Date getCreatedTime(){
		return this.createdTime;
	}
	/** 创建时间 */
	public void setCreatedTime(Date createdTime){
		this.createdTime = createdTime;
	}
	/** 更新人 */
	public String getUpdatedBy(){
		return this.updatedBy;
	}
	/** 更新人 */
	public void setUpdatedBy(String updatedBy){
		this.updatedBy = updatedBy;
	}
	/** 更新时间 */
	public Date getUpdatedTime(){
		return this.updatedTime;
	}
	/** 更新时间 */
	public void setUpdatedTime(Date updatedTime){
		this.updatedTime = updatedTime;
	}
	/** 医馆ID */
	public String getHospitalId(){
		return this.hospitalId;
	}
	/** 医馆ID */
	public void setHospitalId(String hospitalId){
		this.hospitalId = hospitalId;
	}
	/** 员工编号 */
	public String getCode(){
		return this.code;
	}
	/** 员工编号 */
	public void setCode(String code){
		this.code = code;
	}
	/** 员工姓名 */
	public String getName(){
		return this.name;
	}
	/** 员工姓名 */
	public void setName(String name){
		this.name = name;
	}
	/** 类型 */
	public String getType(){
		return this.type;
	}
	/** 类型 */
	public void setType(String type){
		this.type = type;
	}
	/** 状态;0：在职，1：离职 */
	public String getStatus(){
		return this.status;
	}
	/** 状态;0：在职，1：离职 */
	public void setStatus(String status){
		this.status = status;
	}
	/** 专长 */
	public String getDesc(){
		return this.desc;
	}
	/** 专长 */
	public void setDesc(String desc){
		this.desc = desc;
	}
}