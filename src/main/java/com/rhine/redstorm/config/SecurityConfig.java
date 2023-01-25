package com.rhine.redstorm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
public class SecurityConfig {

    @Value("${redstrom.jwks.uri}")
    private String jwksUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http.oauth2ResourceServer(oauth -> oauth.jwt().jwkSetUri(jwksUri));
        
        http.csrf().disable();

        http.authorizeHttpRequests(auth -> {
            auth.anyRequest().authenticated();

        });
        
        return http.build();
    }

}
