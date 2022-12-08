package com.paymentchain.configServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}

/*

java -jar C:\Users\carlo\.m2\repository\com\paymentchain\infraestructuradomain\configserver\0.0.1-SNAPSHOT\configserver-0.0.1-SNAPSHOT.jar

user: admin
password: qwerty

http://localhost:8888/config-client/production
http://localhost:8888/config-client/development

*/