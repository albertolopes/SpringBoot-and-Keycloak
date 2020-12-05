package com.beto.food.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.swagger.web.SecurityConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@Component
public class SecuritySwaggerConfig implements ApplicationListener<DocketEvent> {

    private static final String API_VERSION_REGEX = "/v[0-9]+/.*";

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${swagger.auth.token-url}")
    private String authTokenUrl;

    @Value("${swagger.auth.client-id}")
    private String authClientId;

    @Value("${swagger.auth.secret}")
    private String secret;

    public void onApplicationEvent(DocketEvent docketEvent) {
        docketEvent.getDocket()
                .securitySchemes(singletonList(securitySchema()))
                .securityContexts(singletonList(securityContext()));
    }

    @Bean
    public SecurityConfiguration securityConfiguration() {
        return new SecurityConfiguration(authClientId, secret, realm, authClientId, "", Collections.EMPTY_MAP, false);
    }

    private OAuth securitySchema() {
        List<GrantType> grantTypes = new ArrayList();
        GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant(authTokenUrl);
        grantTypes.add(creGrant);
        return new OAuth(authClientId, Collections.EMPTY_LIST, grantTypes);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Lists.newArrayList(new SecurityReference(authClientId, new AuthorizationScope[0])))
                .forPaths(PathSelectors.regex(API_VERSION_REGEX))
                .build();
    }
}