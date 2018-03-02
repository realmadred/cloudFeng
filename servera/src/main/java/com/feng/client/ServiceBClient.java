package com.feng.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "serverb",fallback = ServiceBClient.ServicebClientBack.class)
public interface ServiceBClient {

    @GetMapping("/b/getb")
    String getb();

    @Component
    class ServicebClientBack implements ServiceBClient{
        @Override
        public String getb() {
            return "出错了...";
        }
    }
}
