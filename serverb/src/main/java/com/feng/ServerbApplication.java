package com.feng;

import com.feng.mq.MySink;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringCloudApplication
@EnableBinding(MySink.class)
// 声明要从 Spring Cloud Bus 监听 RemoteApplicationEvent 事件
@RemoteApplicationEventScan
public class ServerbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerbApplication.class,args);
    }
}
