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
	public User addUser(@RequestBody User user) {
		User user1 = userService.addUser(user);
		return user1;
	}

	@RequestMapping("/updateUser")
	User updateUser(@RequestParam("id") Integer id) {
		User user = userService.updateUser(id);
		return user;
	}

	@RequestMapping("/selectUser")
	User selectUser(@RequestParam("id") Integer id) {
		User user = userService.selectUser(id);
		System.out.println(user.toString());
		return user;
	}

	@RequestMapping("/deleteUser")
	String deleteUser(@RequestParam("id") Integer id) {
		userService.deleteUser(id);
		return "OK";
	}

}
