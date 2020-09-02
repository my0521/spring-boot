package com.my.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.entity.User;

@RestController
public class GoodsController {
	@RequestMapping(value = "/goods", method = RequestMethod.GET)
	public String hello() {
		return "goods";
	}

	@RequestMapping(value = "/userInfo", method = RequestMethod.POST)
	public User userInfo(@RequestBody User user) {
		user.setPassword("goods userInfo");
		return user;
	}
}
