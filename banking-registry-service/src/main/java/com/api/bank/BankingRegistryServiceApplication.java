package com.api.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BankingRegistryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingRegistryServiceApplication.class, args);
	}

}
