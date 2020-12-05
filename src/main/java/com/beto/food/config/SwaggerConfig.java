package com.beto.food.config;

import com.fasterxml.classmate.TypeResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Slf4j
@Configuration
@EnableSwagger2
//@Import(BeanValidatorPluginsConfigurtion.class)
public class SwaggerConfig {

    private final TypeResolver typeResolver;

    private final ApplicationEventPublisher publisher;

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

    public SwaggerConfig(TypeResolver typeResolver, ApplicationEventPublisher publisher) {
        this.typeResolver = typeResolver;
        this.publisher = publisher;
    }

    public Docket criarDocket(String apiVersion) {
        try {
            Docket docket =  new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.beto.food"))
                    .paths(PathSelectors.regex("/" + apiVersion + "/.*"))
                    .build()
                    .groupName(apiVersion)
                    .produces(newHashSet(APPLICATION_JSON))
                    .apiInfo(apiInfo())
                    .forCodeGeneration(true)
                    .ignoredParameterTypes(AuthenticationPrincipal.class)
                    .groupName(apiVersion)
                    .pathMapping("/")
                    .directModelSubstitute(LocalDate.class, Date.class)
                    .directModelSubstitute(LocalDateTime.class, java.util.Date.class)
                    .genericModelSubstitutes(ResponseEntity.class)
                    .alternateTypeRules(
                            newRule(typeResolver.resolve(DeferredResult.class,
                                    typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                                    typeResolver.resolve(WildcardType.class)))
                    .useDefaultResponseMessages(true)
                    .globalResponseMessage(RequestMethod.GET, globalResponses())
                    .globalResponseMessage(RequestMethod.POST, globalResponses())
                    .globalResponseMessage(RequestMethod.DELETE, globalResponses())
                    .globalResponseMessage(RequestMethod.PUT, globalResponses())
                    .globalResponseMessage(RequestMethod.PATCH, globalResponses());

            publisher.publishEvent(new DocketEvent(this, docket));
            return docket;
        }
        catch (Exception e){
            return null;
        }
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
        return newArrayList(
                new ResponseMessageBuilder().code(500).message("Erro inesperado no servidor, não foi possível processar a solicitação").build()
                , new ResponseMessageBuilder().code(400).message("Dados inválidos fornecidos pelo cliente").build()
                , new ResponseMessageBuilder().code(401).message("Usuário não autenticado").build()
                , new ResponseMessageBuilder().code(403).message("Usuário sem permissão para esta operação").build()
        );
    }
}