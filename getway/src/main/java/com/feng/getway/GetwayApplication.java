package com.feng.getway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringCloudApplication
@EnableZuulProxy
public class GetwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetwayApplication.class,args);
    }
}
