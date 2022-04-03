package com.api.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableEurekaClient
@EnableMongoRepositories
public class BankingTransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingTransactionServiceApplication.class, args);
	}

}
