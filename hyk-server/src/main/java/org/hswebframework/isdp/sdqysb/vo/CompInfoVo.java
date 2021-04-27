package org.hswebframework.isdp.sdqysb.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 企业填报信息vo
 */
@Getter
@Setter
public class CompInfoVo implements Serializable {
    private String compId;
    private String usccId;
    private String regTime;
    private String regCapital;
    private String regCity;
    private String regCounty;
    private String regAddress;
    private String compNature;
    private String industry;
    private String compIntroduction;
    private String standardTypeId;
    private String standardType;
    private String isGuidance;
    private String isOpen;
    private String isDelete;
    private String createUserId;
    private String createTime;
    private String lastUpdateTime;
    private String version;
    private String status;


    private String majorBussiness;
    private String ownStatus;
    private String incomePercent;
}
