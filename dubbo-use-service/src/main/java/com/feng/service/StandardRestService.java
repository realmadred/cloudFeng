///*
// * Copyright 2013-2018 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.feng.service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
//import javax.ws.rs.HeaderParam;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.core.MediaType;
//
//import com.feng.api.RestService;
//import com.feng.dto.UserDto;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.dubbo.config.annotation.Service;
//
//import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
//
///**
// * Default {@link RestService}.
// *
// * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
// */
//@Service(version = "1.0.0", protocol = { "dubbo", "rest" })
//@Path("/")
//@Slf4j
//public class StandardRestService implements RestService {
//
//
//	@Override
//	@Path("param")
//	@GET
//	public String param(@QueryParam("param") String param) {
//		log.info("/param:{}", param);
//		return param;
//	}
//
//	@Override
//	@Path("params")
//	@POST
//	public String params(@QueryParam("a") int a, @QueryParam("b") String b) {
//		log.info("/params:{}", a + b);
//		return a + b;
//	}
//
//	@Override
//	@Path("headers")
//	@GET
//	public String headers(@HeaderParam("h") String header,
//			@HeaderParam("h2") String header2, @QueryParam("v") Integer param) {
//		String result = header + " , " + header2 + " , " + param;
//		log.info("/headers:{}", result);
//		return result;
//	}
//
//	@Override
//	@Path("path-variables/{p1}/{p2}")
//	@GET
//	public String pathVariables(@PathParam("p1") String path1,
//			@PathParam("p2") String path2, @QueryParam("v") String param) {
//		String result = path1 + " , " + path2 + " , " + param;
//		log.info("/path-variables:{}", result);
//		return result;
//	}
//
//	// @CookieParam does not support : https://github.com/OpenFeign/feign/issues/913
//	// @CookieValue also does not support
//
//	@Override
//	@Path("form")
//	@POST
//	public String form(@FormParam("f") String form) {
//		return String.valueOf(form);
//	}
//
//	@Override
//	@Path("request/body/map")
//	@POST
//	@Produces(APPLICATION_JSON_VALUE)
//	public UserDto requestBodyMap(Map<String, Object> data,
//								  @QueryParam("param") String param) {
//		UserDto user = new UserDto();
//		user.setId(((Integer) data.get("id")).longValue());
//		user.setName((String) data.get("name"));
//		user.setAge((Integer) data.get("age"));
//		log.info("/request/body/map:{}", param);
//		return user;
//	}
//
//	@Path("request/body/user")
//	@POST
//	@Override
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Map<String, Object> requestBodyUser(UserDto user) {
//		Map<String, Object> map = new HashMap<>();
//		map.put("id", user.getId());
//		map.put("name", user.getName());
//		map.put("age", user.getAge());
//		return map;
//	}
//
//}
