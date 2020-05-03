/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.feng.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.feng.api.UserService;
import com.feng.dto.UserDto;
import org.apache.dubbo.config.annotation.Service;

/**
 * In-Memory {@link UserService} implementation.
 */
@Service(protocol = "dubbo")
public class InMemoryUserService implements UserService {

	private Map<Long, UserDto> usersRepository = new HashMap<>();

	@Override
	public UserDto getUser(Long id) {
		return usersRepository.get(id);
	}

	@Override
	public boolean save(UserDto user) {
		return usersRepository.put(user.getId(), user) == null;
	}

	@Override
	public boolean remove(Long userId) {
		return usersRepository.remove(userId) != null;
	}

	@Override
	public Collection<UserDto> findAll() {
		return usersRepository.values();
	}

}
