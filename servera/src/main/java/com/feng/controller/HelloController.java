package com.feng.controller;

import com.feng.client.ServiceBClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/hello")
public class HelloController {

    private final static Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private ServiceBClient bClient;

    @Value("${info.name}")
    private String name;

    @Value("${info.age}")
    private String age;

    @RequestMapping("/say")
    public String say(){
        LOGGER.info("say name:{},age{}",name,age);
        return "hello"+name+age;
    }

    @RequestMapping("/remote")
    public String remote(){
        System.out.println("say...");
        return bClient.getB();
    }
}
