package com.my.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
//@Api
//@ApiResponses({ @ApiResponse(code = 200, message = "success", response = ResponseEntity.class) })
public class UserController {
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		return "hello";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User userInfo(@RequestBody User user) {
		user.setPassword("test");
		return user;
	}
}
