package com.rhine.redstorm;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedstromConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
