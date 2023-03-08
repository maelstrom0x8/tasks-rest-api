package com.rhine.redstorm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.rhine.*")
@EntityScan(basePackages = "com.rhine.*")
@EnableJpaRepositories(basePackages = "com.rhine.*")
public class RedstormApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedstormApplication.class, args);
	}

}
