package org.aeros.tasks.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    @Value("${rhine.jwks.uri:http://localhost:9000/oauth2/jwks}")
    private String jwksUri;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.oauth2ResourceServer(oauth -> oauth.jwt(jwt -> jwt.jwkSetUri(jwksUri)));

        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(
                auth -> {
                    auth.requestMatchers("/actuator/**").permitAll();
                    auth.anyRequest().authenticated();
                });

        return http.build();
    }
}
