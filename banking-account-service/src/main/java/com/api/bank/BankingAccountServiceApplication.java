package com.api.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class BankingAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingAccountServiceApplication.class, args);
	}

}
