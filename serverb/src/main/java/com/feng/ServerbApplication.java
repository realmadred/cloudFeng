package com.feng;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringCloudApplication
@EnableOAuth2Client
public class ServerbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerbApplication.class,args);
    }
}
