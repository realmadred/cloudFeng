package com.feng.controller;

import com.feng.bus.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bus.ServiceMatcher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@Slf4j
public class BusController {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ServiceMatcher busServiceMatcher;

    @GetMapping("/register")
    public String register(String username) {
        // ... 执行注册逻辑
        log.info("[register][执行用户({}) 的注册逻辑]", username);

        // ... <2> 发布
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this, busServiceMatcher.getServiceId(),
                null, username)); // <1>
        return "success";
    }


}