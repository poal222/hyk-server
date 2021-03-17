package org.isdp.hyk.hospital.entity;

import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Table(name="hospital_duration")
public class HospitalDuration extends GenericEntity<String> {
	/** 大夫id */
	private String doctorId ;
	/** 坐诊日期;精确到日 */
	private String date ;
	/** 计划诊疗数 */
	private String planNums ;
	/** 状态;0:启用，1：不启用 */
	private String status ;
	/** 时间类型;1:上午；2：下午 */
	private String type ;

	/** 大夫id */
	public String getDoctorId(){
		return this.doctorId;
	}
	/** 大夫id */
	public void setDoctorId(String doctorId){
		this.doctorId = doctorId;
	}
	/** 坐诊日期;精确到日 */
	public String getDate(){
		return this.date;
	}
	/** 坐诊日期;精确到日 */
	public void setDate(String date){
		this.date = date;
	}
	/** 计划诊疗数 */
	public String getPlanNums(){
		return this.planNums;
	}
	/** 计划诊疗数 */
	public void setPlanNums(String planNums){
		this.planNums = planNums;
	}
	/** 状态;0:启用，1：不启用 */
	public String getStatus(){
		return this.status;
	}
	/** 状态;0:启用，1：不启用 */
	public void setStatus(String status){
		this.status = status;
	}
	/** 时间类型;1:上午；2：下午 */
	public String getType(){
		return this.type;
	}
	/** 时间类型;1:上午；2：下午 */
	public void setType(String type){
		this.type = type;
	}
}