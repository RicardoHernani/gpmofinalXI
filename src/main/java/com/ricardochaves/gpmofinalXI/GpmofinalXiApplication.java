package com.ricardochaves.gpmofinalXI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.ricardochaves.domain")
@ComponentScan(basePackages = { "com.ricardochaves.*" })
@EnableJpaRepositories(basePackages = { "com.ricardochaves.repositories" })
public class GpmofinalXiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GpmofinalXiApplication.class, args);
	}

}

