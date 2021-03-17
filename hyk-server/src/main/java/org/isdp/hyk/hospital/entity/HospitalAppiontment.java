package org.isdp.hyk.hospital.entity;

import lombok.Getter;
import lombok.Setter;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.*;


@Table(name="hospital_appiontment")
@Getter
@Setter
public class HospitalAppiontment extends GenericEntity<String> {
	/** 预约日期 */
	private String date ;
	/** 日期类型 */
	private String type ;
	/** 预约大夫id */
	private String doctorId ;
	/** 预约病患 */
	private String patientId ;
	/** 预约就诊单id */
	private String treatmentId ;
	/** 状态 */
	private String status ;


}