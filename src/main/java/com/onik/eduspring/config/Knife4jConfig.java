package com.onik.eduspring.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("在线教育系统接口文档")
                        .description("基于Spring Boot + Vue的在线教育平台API文档")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("李家辉")
                                .email("onik060006@gmail.com")
                        )
                        .license(new License().name("Apache 2.0"))
                );
    }
}