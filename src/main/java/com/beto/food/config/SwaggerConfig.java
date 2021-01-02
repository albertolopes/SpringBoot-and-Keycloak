package com.beto.food.config;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String nomeProjeto;

    @Bean
    public Docket apiV1() {
        return criarDocket("v1");
    }

    @Bean
    public Docket apiV2() {
        return criarDocket("v2");
    }

    public Docket criarDocket(String apiVersion) {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.beto.food"))
                .paths(PathSelectors.regex("/" + apiVersion + "/.*"))
                .build()
                .groupName(apiVersion)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(nomeProjeto)
                .build();
    }

    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private List<ResponseMessage> globalResponses() {
        return Lists.newArrayList(
                new ResponseMessageBuilder().code(500).message("Internal Error").build()
                , new ResponseMessageBuilder().code(400).message("Invalid date").build()
                , new ResponseMessageBuilder().code(401).message("User do not was authenticated").build()
                , new ResponseMessageBuilder().code(403).message("User without permission for this operation").build()
        );
    }

}