package com.feng;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringCloudApplication
@EnableFeignClients
@EnableOAuth2Client
public class ServiceaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceaApplication.class,args);
    }
}
