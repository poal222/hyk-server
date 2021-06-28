package org.hswebframework.isdp.authentication.captcha;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@ConfigurationProperties(prefix = "captcha")
@Getter
@Setter
public class CaptchaProperties {

    //是否开启验证码
    private boolean enabled = false;

    //过期时间
    private Duration ttl = Duration.ofMinutes(2);

    //验证码
    private CaptchaType type = CaptchaType.image;

    public enum CaptchaType {
        image
    }
}
