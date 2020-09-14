package com.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.entity.User;
import com.my.service.UserService;

@RestController
public class UserController {	
	@Autowired
	UserService userService;

	//http://localhost:8080/addUser  {"id": "1","name":"name1"}
	@RequestMapping("/addUser")
	public String addUser(@RequestBody User user) {
		userService.addUser(user);
		return "OK";
	}

	@RequestMapping("/updateUser")
	String updateUser(@RequestParam("id") Integer id) {
		userService.updateUser(id);
		return "OK";
	}

	@RequestMapping("/selectUser")
	String selectUser(@RequestParam("id") Integer id) {
		userService.selectUser(id);
		return "OK";
	}

	@RequestMapping("/deleteUser")
	String deleteUser(@RequestParam("id") Integer id) {
		userService.deleteUser(id);
		return "OK";
	}

}
