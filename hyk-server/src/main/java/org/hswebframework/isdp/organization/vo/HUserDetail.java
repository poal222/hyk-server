package org.hswebframework.isdp.organization.vo;

import lombok.Getter;
import lombok.Setter;
import org.hswebframework.isdp.organization.entity.UserDetailEntity;
import org.hswebframework.web.system.authorization.api.entity.UserEntity;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class HUserDetail implements Serializable {
    private String username;
    private String password;
    private String nikename;
    private String mobile;
    private String agreement;
    private String captcha;
    private String city;
    private String type;


    public UserEntity converToUserEntity(){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setUsername(this.getUsername());
        userEntity.setPassword(this.getPassword());
        userEntity.setName(this.getNikename());
//        userEntity.setCreateTime(Long.parseLong(System::currentTimeMillis));
        userEntity.setStatus(Byte.decode("1"));//启用
        userEntity.setCreateTimeNow();
        userEntity.setUsername(this.getUsername());

        userEntity.setType(this.type);
        return userEntity;

    };
    public UserDetailEntity converToUserDetailEntity(){
        UserDetailEntity userEntity = new UserDetailEntity();
        userEntity.setName(this.getNikename());
        userEntity.setTelephone(this.getMobile());
        return userEntity;

    };
}
