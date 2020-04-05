package com.feng.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "serverb",fallback = ServiceBClient.ServiceBClientFallback.class)
public interface ServiceBClient {

    @GetMapping("/b/getB")
    String getB();

    @Component
    class ServiceBClientFallback implements ServiceBClient{
        @Override
        public String getB() {
            return "ServiceBClientFallback";
        }
    }

}
