package org.hswebframework.isdp.hyk.hospital.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Table;


@Table(name="hospital_appiontment")
@Getter
@Setter
public class HospitalAppiontment extends GenericEntity<String> {
	/** 预约日期 */
	@Schema(description = "预约日期")
	@Column
	private String date ;
	/** 日期类型 */
	@Column
	@Schema(description = "日期类型")
	private String type ;
	/** 预约大夫id */
	@Column
	@Schema(description = "预约大夫id")
	private String doctorId ;
	/** 预约病患 */
	@Column
	@Schema(description = "预约病患")
	private String patientId ;

	/** 预约就诊单id */
	@Column
	@Schema(description = "预约就诊单id")
	private String treatmentId ;
	/** 状态 */
	@Column
	@Schema(description = "状态")
	private String status ;


}