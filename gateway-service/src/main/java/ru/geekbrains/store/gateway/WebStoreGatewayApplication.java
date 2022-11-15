package ru.geekbrains.store.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://cloud.spring.io/spring-cloud-gateway/reference/html/

@SpringBootApplication
public class WebStoreGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebStoreGatewayApplication.class, args);
    }
}