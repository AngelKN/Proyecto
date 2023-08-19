package com.gateway.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class ServicioGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioGatewayApplication.class, args);
	}

}
