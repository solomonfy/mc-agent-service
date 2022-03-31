package com.medochemie.ordermanagement.agentservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerConfiguration() {
        Set<String> responseProduceType = new HashSet<String>();
        responseProduceType.add("application/json");
        responseProduceType.add("application/xml");
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.ant("/api/v1/agents/*"))
                .build()
                .useDefaultResponseMessages(false)
                .genericModelSubstitutes(ResponseEntity.class)
                .produces(responseProduceType)
                .consumes(responseProduceType)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "MC Agents API",
                "API for managing agents information",
                "1.0.0",
                "Only for internal use",
                new springfox.documentation.service.Contact(
                        "Medochemie Ltd",
                        "https://www.medochemie.com/",
                        "https://www.medochemie.com/ContactUs.aspx"),
                "API License - for internal use only",
                "https://www.medochemie.com/",
                Collections.emptyList()
        );
    }

    private Object apiKey() {
        // TODO Auto-generated method stub
        return null;

    }

}
