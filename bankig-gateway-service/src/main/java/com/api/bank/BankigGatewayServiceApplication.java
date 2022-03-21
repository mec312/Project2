package com.api.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BankigGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankigGatewayServiceApplication.class, args);
	}

}
