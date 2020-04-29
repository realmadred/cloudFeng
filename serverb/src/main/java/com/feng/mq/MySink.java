package com.feng.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {

    String INPUT = "sb-input";

    @Input(INPUT)
    SubscribableChannel sbInput();

}