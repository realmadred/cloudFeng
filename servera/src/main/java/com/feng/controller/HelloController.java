package com.feng.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.feng.client.ServiceBClient;
import com.feng.meter.MyMeter;
import io.micrometer.core.instrument.binder.MeterBinder;
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

    @Autowired
    private MyMeter binder;

    @Value("${info.name}")
    private String name;

    @Value("${info.age}")
    private String age;

    @RequestMapping("/say")
    @SentinelResource("hello/say")
    public String say() throws InterruptedException {
        LOGGER.info("say name:{},age{}",name,age);
        for (int i = 0; i < 1000; i++) {
            binder.counter1.increment(Math.random());
            binder.counter2.increment(Math.random());
            binder.counter3.increment(Math.random());
            Thread.sleep(200);
        }

        return "hello"+name+age;
    }

    @RequestMapping("/remote")
    @SentinelResource(value = "hello/remote")
    public String remote(){
        System.out.println("say...");
        return bClient.getB();
    }
}
