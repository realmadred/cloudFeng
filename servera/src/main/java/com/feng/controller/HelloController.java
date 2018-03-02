package com.feng.controller;

import com.feng.client.ServiceBClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    ServiceBClient bClient;

    @RequestMapping("/say")
    public String say(){
        System.out.println("say...");
        return "hello";
    }

    @RequestMapping("/remote")
    public String remote(){
        System.out.println("say...");
        return bClient.getb();
    }
}
