package com.my.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.entity.User;
import com.my.service.UserService;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;


@RestController
//@Api(value = "/",description = "UserController")
public class UserController {
	@Resource
	UserService userService;

	@ResponseBody
	@PostMapping(value = "/addUser")
    //@ApiOperation(value = "添加一个User",httpMethod ="POST")
	public String addUser(@RequestParam("name") String name,@RequestParam("age") int age) {		
		User usr  = new User();
		usr.setName(name);
		usr.setAge(age);
		userService.addUser(usr);
		return usr.toString();
	}
	
	@ResponseBody
	@GetMapping(value = "/list")
	//@ApiOperation(value = "获取所有User列表",httpMethod ="GET")
	public String list() {		
		List<User> list = userService.list();
		return list.toString();
	}
}
