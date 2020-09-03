package com.my.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.my.entity.User;
import com.my.service.UserService;



@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/selectByKey/{id}")
	public String selectByPrimaryKey(@PathVariable Integer id) {
		User user = userService.selectByPrimaryKey(id);
		return user.toString();		
	}
}
