package com.my.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.entity.User;
import com.my.service.IUserService;

@RequestMapping("user")
@RestController
public class UserController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("/select")
	public List<User> select() {
		
		return userService.getUserList();
	}
	
	@GetMapping("/insert")
	public boolean insert(User user){
		
		return userService.save(user);
	}	
}
