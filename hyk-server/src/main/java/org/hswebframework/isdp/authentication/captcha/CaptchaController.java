package org.hswebframework.isdp.authentication.captcha;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.events.AuthorizationDecodeEvent;
import org.hswebframework.web.exception.ValidationException;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@Authorize(ignore = true)
@AllArgsConstructor
@RequestMapping("/authorize/captcha")
@Tag(name = "验证码接口")
public class CaptchaController {

    private final CaptchaProperties properties;

    private final ReactiveRedisOperations<String, String> redis;

    @GetMapping("/config")
    public Mono<CaptchaConfig> createCaptcha() {
        CaptchaConfig captchaConfig=new CaptchaConfig();
        captchaConfig.setEnabled(properties.isEnabled());
        captchaConfig.setType(properties.getType().name());
        return Mono.just(captchaConfig);
    }

    @GetMapping("/image")
    public Mono<CaptchaInfo> createCaptcha(@RequestParam(defaultValue = "130") int width,
                                           @RequestParam(defaultValue = "40") int height) {
        if (!properties.isEnabled()) {
            return Mono.empty();
        }
        SpecCaptcha captcha = new SpecCaptcha(width, height, 4);
        captcha.setCharType(Captcha.TYPE_DEFAULT);

        String base64 = captcha.toBase64();
        String key = UUID.randomUUID().toString();
        System.out.println( captcha.text());
        return redis
            .opsForValue()
            .set("captcha:" + key, captcha.text(), properties.getTtl())
            .thenReturn(new CaptchaInfo(key, base64));
    }

    @EventListener
    public void handleAuthEvent(AuthorizationDecodeEvent event) {
        if (!properties.isEnabled()) {
            return;
        }
        String key = event.getParameter("verifyKey").map(String::valueOf).orElseThrow(() -> new ValidationException("验证码错误"));
        String code = event.getParameter("verifyCode").map(String::valueOf).orElseThrow(() -> new ValidationException("验证码错误"));
        String redisKey = "captcha:" + key;
        event.async(
            redis
                .opsForValue()
                .get(redisKey)
                .map(code::equalsIgnoreCase)
                .defaultIfEmpty(false)
                .flatMap(checked -> redis
                    .delete(redisKey)
                    .then(checked ? Mono.empty() : Mono.error(new ValidationException("验证码错误"))))
        );

    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CaptchaInfo {
        private String key;

        private String base64;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CaptchaConfig{
        private boolean enabled;

        private String type;
    }
}
