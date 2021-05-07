package org.hswebframework.isdp.organization.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class IsdpPassword implements Serializable {
    private String userId;
    private String oldPassword;
    private String newPassword;
}
