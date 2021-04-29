package org.hswebframework.isdp.sdqysb.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;

/**
 * 当前审批节点的信息
 */
@Getter
@Setter
public class NodeInfo implements Serializable {

    private String status;
    private String result;
    private String viewinfo;
}
