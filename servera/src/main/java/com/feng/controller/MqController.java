package com.feng.controller;

import com.feng.mq.MySource;
import com.feng.mq.SaMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/mq")
@RequiredArgsConstructor
public class MqController {

    private final MySource mySource;

    @SneakyThrows
    @GetMapping("/send")
    public boolean send() {
        // <1> 创建 Message
        for (int i = 0; i < 10; i++) {
            SaMessage message = new SaMessage()
                    .setId(i).setName(RandomStringUtils.randomAlphanumeric(6))
                    .setAddress(RandomStringUtils.randomAlphabetic(10));
            // <2> 创建 Spring Message 对象
            Message<SaMessage> springMessage = MessageBuilder.withPayload(message)
                    .setCorrelationId(UUID.randomUUID().toString())
                    .build();
            mySource.saOutput().send(springMessage);
        }
        // <3> 发送消息
        return true;
    }
}
