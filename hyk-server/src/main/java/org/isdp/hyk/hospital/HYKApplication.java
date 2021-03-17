package org.isdp.hyk.hospital;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.hswebframework.web.crud.annotation.EnableEasyormRepository;
import org.springdoc.webflux.core.SpringDocWebFluxConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableEasyormRepository("org.isdp.hyk.**.entity")
public class HYKApplication {

    public static void main(String[] args) {
        SpringApplication.run(HYKApplication.class, args);
    }

    @Configuration(proxyBeanMethods = false)
    @OpenAPIDefinition(
            info = @Info(
                    title = "华医酷后台管理系统",
                    description = "华医酷后台管理系统",
                    contact = @Contact(name = "admin"),
                    version = "1.0.0-SNAPSHOT"
            )
    )
    @AutoConfigureBefore(SpringDocWebFluxConfiguration.class)
    static class SwaggerConfiguration {

    }
}
