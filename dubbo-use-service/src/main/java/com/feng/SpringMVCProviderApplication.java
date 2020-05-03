package com.feng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringMVCProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMVCProviderApplication.class);
    }

}