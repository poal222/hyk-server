package org.hswebframework.isdp.sdqysb.entity;

import lombok.Getter;
import lombok.Setter;
import org.hswebframework.web.api.crud.entity.GenericEntity;

import javax.persistence.Table;

@Table(name = "comp_finance")
@Getter
@Setter
public class CompFinance extends GenericEntity<String> {
    /**
     * 企业ID
     */
    private String compId;
    /**
     * 财务年度
     */
    private String year;
    /**
     * 营业收入
     */
    private String income;
    /**
     * 净利润
     */
    private String net;
    /**
     * 研发投入
     */
    private String investment;
    /**
     * 总资产
     */
    private String totalAssest;
    /**
     * 净资产
     */
    private String netAssest;
    /**
     * 删除标识
     */
    private String isDelete;
    /**
     * 创建日期
     */
    private String createTime;
    /**
     * 最后一次更新时间
     */
    private String lastUpdateTime;
    /**
     * 创建人
     */
    private String createUserId;
    /**
     * 提报版本
     */
    private String version;

}
