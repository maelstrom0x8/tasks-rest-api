package com.rhine.redstorm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class TestConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails u1 = User.withUsername("anna")
                .password(passwordEncoder().encode("anna"))
                .authorities("read")
                .build();

        UserDetails u2 = User.withUsername("king")
                .password(passwordEncoder().encode("king"))
                .authorities("read", "write")
                .build();

        return new InMemoryUserDetailsManager(u1, u2);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
