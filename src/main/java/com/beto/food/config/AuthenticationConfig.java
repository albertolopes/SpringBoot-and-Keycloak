package com.beto.food.config;

import com.beto.food.service.UserService;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AuthenticationConfig {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private UserService userService;

    public String getLoggedUser(){
        return getLogin();
    }

    private String getLogin() {
        return getSecurityContext().getToken().getPreferredUsername();
    }

    private KeycloakSecurityContext getSecurityContext() {
        KeycloakSecurityContext security = securityConfig.provideKeycloakSecurityContext();
        return security;
    }

    public AccessToken getKeycloakUser(){
        AccessToken access = getSecurityContext().getToken();
        return access;
    }

    public boolean hasRole(String role) {
        AccessToken.Access access = getSecurityContext().getToken().getRealmAccess();
        Set<String> roles = access.getRoles();
        if (roles == null || roles.isEmpty()) {
            return false;
        } else {
            return roles.contains(role);
        }
    }

}
