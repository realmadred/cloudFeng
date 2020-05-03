package com.feng.api;

import com.feng.dto.UserDto;

import java.util.Collection;

public interface UserService {

    UserDto getUser(Long id);

    boolean save(UserDto user);

    boolean remove(Long userId);

    Collection<UserDto> findAll();

}
