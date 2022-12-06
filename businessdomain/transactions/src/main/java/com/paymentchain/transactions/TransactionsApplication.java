package com.paymentchain.transactions;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class TransactionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionsApplication.class, args);
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springshop-public")
                .packagesToScan("com.paymentchain")
                .build();
    }
}

/*

URL Swagger: http://localhost:8082/swagger/index.html
java -jar C:\Users\carlo\.m2\repository\com\paymentchain\businessdomain\transactions\0.0.1-SNAPSHOT\transactions-0.0.1-SNAPSHOT.jar

 */
