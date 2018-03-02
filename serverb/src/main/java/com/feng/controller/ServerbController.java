package com.feng.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/b")
public class ServerbController {

    @GetMapping("/getb")
    public String getb(){
        System.out.println("getb...");
        return "my hello b";
    }
}
