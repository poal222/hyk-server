package org.hswebframework.isdp.sdqysb.vo;

import lombok.Getter;
import lombok.Setter;
import org.hswebframework.isdp.sdqysb.entity.CompAuditing;

import java.io.Serializable;

/**
 * 当前审批节点的信息
 */
@Getter
@Setter
public class WfNodeInfo implements Serializable {
    private CompAuditing compAuditing;
    private NodeInfo nodeInfo;
}
