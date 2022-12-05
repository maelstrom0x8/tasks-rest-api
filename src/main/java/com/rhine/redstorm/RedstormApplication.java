package com.rhine.redstorm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.rhine.redstorm.*")
public class RedstormApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedstormApplication.class, args);
	}

}
