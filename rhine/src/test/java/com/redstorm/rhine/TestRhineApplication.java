package com.redstorm.rhine;

import org.springframework.boot.SpringApplication;

public class TestRhineApplication {

    public static void main(String[] args) {
        SpringApplication.from(RhineApplication::main).with(TestContainersConfig.class).run(args);
    }
}
