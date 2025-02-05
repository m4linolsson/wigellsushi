package com.example.wigellsushi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WigellSushiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WigellSushiApplication.class, args);
        System.out.println("tweet tweet");
    }


    @Bean
  //  @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
