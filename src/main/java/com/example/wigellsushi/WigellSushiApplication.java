package com.example.wigellsushi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WigellSushiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WigellSushiApplication.class, args);
        System.out.println("tweet tweet");


    }

    //Gör att vi kan
    @Bean
    @LoadBalanced //säger att vi ska använda en loadbalancer client (ex eureka) så vi kan använda service name i url ist för host och port...
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
