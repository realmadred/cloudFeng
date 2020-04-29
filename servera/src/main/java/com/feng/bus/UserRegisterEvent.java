package com.feng.bus;

import lombok.Data;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

@Data
public class UserRegisterEvent extends RemoteApplicationEvent {

    /**
     * 用户名
     */
    private String username;

    public UserRegisterEvent() {
    }

    public UserRegisterEvent(Object source, String originService, String destinationService, String username) {
        super(source, originService,destinationService);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}