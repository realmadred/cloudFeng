package com.feng.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/b")
public class ServerbController {

    @GetMapping("/getB")
    @SentinelResource("b/getB")
    public String getb(){
        System.out.println("getB...");
        return "my hello b";
    }
}
