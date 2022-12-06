package com.paymentchain.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaserverApplication.class, args);
    }
}

/*

java -jar C:\Users\carlo\.m2\repository\com\paymentchain\infraestructuradomain\eurekaserver\0.0.1-SNAPSHOT\eurekaserver-0.0.1-SNAPSHOT.jar
 
*/