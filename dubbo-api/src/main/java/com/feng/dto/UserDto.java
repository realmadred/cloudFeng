package com.feng.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = -1872437316818512141L;

    private Long id;

    private String name;

    private Integer age;

}
