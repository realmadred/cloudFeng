package com.feng.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "serverb")
public interface ServiceBClient {

    @GetMapping("/b/getB")
    String getB();
}
