package org.hswebframework.isdp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.hswebframework.web.authorization.basic.configuration.EnableAopAuthorize;
import org.hswebframework.web.authorization.events.AuthorizingHandleBeforeEvent;
import org.hswebframework.web.crud.annotation.EnableEasyormRepository;
import org.hswebframework.web.logging.aop.EnableAccessLogger;
import org.hswebframework.web.logging.events.AccessLoggerAfterEvent;
import org.springdoc.webflux.core.SpringDocWebFluxConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableEasyormRepository("org.hswebframework.**.entity")
@EnableAccessLogger //启动异步日志
@EnableAopAuthorize //开启AOP权限控制
@EnableCaching  //开启cache
public class CrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }


    @Configuration(proxyBeanMethods = false)
    @OpenAPIDefinition(
            info = @Info(
                    title = "上市企业储备库系统",
                    description = "上市企业储备库系统",
                    contact = @Contact(name = "admin"),
                    version = "1.0.0-SNAPSHOT"
            )
    )
    @AutoConfigureBefore(SpringDocWebFluxConfiguration.class)
    static class SwaggerConfiguration {

    }

	@Profile("dev")
	@Component
	@Slf4j
	public static class AdminAllAccess {

		@EventListener
		public void handleAuthEvent(AuthorizingHandleBeforeEvent e) {
			if (e.getContext().getAuthentication().getUser().getUsername().equals("admin")) {
				e.setAllow(true);
			}
		}

		@EventListener
		public void handleAccessLogger(AccessLoggerAfterEvent event) {

			log.info("{}=>{} {}-{}", event.getLogger().getIp(), event.getLogger().getUrl(), event.getLogger().getDescribe(), event.getLogger().getAction());

		}
	}
}
