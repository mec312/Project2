package com.api.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@EnableEurekaClient
@SpringBootApplication
public class BankingProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingProductServiceApplication.class, args);
    }

}
