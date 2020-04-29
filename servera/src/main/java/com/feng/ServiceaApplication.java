package com.feng;

import com.feng.mq.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringCloudApplication
@EnableFeignClients
// 声明指定接口开启 Binding 功能，扫描其 @Input 和 @Output 注解
@EnableBinding(MySource.class)
public class ServiceaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceaApplication.class,args);
    }
}
