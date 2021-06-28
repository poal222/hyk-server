package org.hswebframework.isdp.sdqysb.vo;

import lombok.Getter;
import lombok.Setter;
import org.hswebframework.isdp.sdqysb.entity.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CompWfInfoVo implements Serializable {

    private CompAuditing compAuditing;
    private CompBasicInfo compBasicInfo;
    private List<CompBusinessInfo> compBusinessInfoList;
    private List<CompFinance> compFinanceList;
    private CompHonorInfo compHonorInfo;
    private List<CompIpoInfo> compIpoInfoList;
    private List<CompPersonInfo> personInfoList;
    private List<CompStandard> compStandardList;
    private List<CompQuality> compQualityList;
    private CompStock compStock;
}
