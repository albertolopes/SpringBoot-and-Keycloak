package com.beto.food.service;

import com.beto.food.config.KeycloakConfig;
import com.beto.food.config.SecurityConfig;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AuthenticationService {

    public final static String ADMIN = "ADMIN";
    public final static String USER = "USER";

    @Autowired
    private SecurityConfig securityConfig;

    private KeycloakSecurityContext getKeycloakPrincipal() {
        KeycloakSecurityContext security = securityConfig.provideKeycloakSecurityContext();
        return security;
    }

    public boolean hasRole(String role) {
        AccessToken.Access access = getKeycloakPrincipal().getToken().getRealmAccess();
        Set<String> roles = access.getRoles();
        if (roles == null || roles.isEmpty()) {
            return false;
        } else {
            return roles.contains(role);
        }
    }


}
