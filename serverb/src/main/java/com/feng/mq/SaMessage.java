package com.feng.mq;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SaMessage {

    /**
     * id
     */
    private Integer id;

    /**
     * id
     */
    private String name;

    /**
     * id
     */
    private String address;


}