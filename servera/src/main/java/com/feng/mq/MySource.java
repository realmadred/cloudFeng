package com.feng.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySource {

    /**
     * 名字需要对应 spring.cloud.stream.bindings
     * @date 2020年4月29日
     * @return
     */
    @Output("sa-output")
    MessageChannel saOutput();

}