package com.api.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableMongoRepositories
@EnableCaching
public class BankingYankiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingYankiServiceApplication.class, args);
	}

}
