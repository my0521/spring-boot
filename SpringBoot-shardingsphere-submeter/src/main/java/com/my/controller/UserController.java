package com.my.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.my.entity.User;
import com.my.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/list")
	public String list() {
		List<User> list = service.selectAll();
		return list.toString();
	}

	/**
	 * 模拟插入数据
	 */
	List<User> userList = Lists.newArrayList();

	/**
	 * 初始化插入数据
	 */
	@PostConstruct
	private void getData() {
		userList.add(new User(1L, "小小", "女", 3));
		userList.add(new User(2L, "爸爸", "男", 30));
		userList.add(new User(3L, "妈妈", "女", 28));
		userList.add(new User(4L, "爷爷", "男", 64));
		userList.add(new User(5L, "奶奶", "女", 62));
	}
	/**
	 * @Description: 批量保存用户
	 */
	@PostMapping("save-user")
	public Object saveUser() {
		return service.insertForeach(userList);
	}
	@GetMapping("deleteByPrimaryKey/{id}")
	public int deleteByPrimaryKey(@PathVariable Long id) {
		return service.deleteByPrimaryKey(id);
	}

	@PostMapping("updateByPrimaryKeySelective")
	@ResponseBody
	public int updateByPrimaryKeySelective(@RequestBody User record) {
		return service.updateByPrimaryKeySelective(record);
	}
	@PostMapping("updateByPrimaryKey")
	@ResponseBody
	public int updateByPrimaryKey(@RequestBody User record) {
		return service.updateByPrimaryKey(record);
	}
	@PostMapping("insertone")
	@ResponseBody
	public int insertone(@RequestBody User user) {
		System.out.println(user.toString());
		return service.insertone(user);
	}
}
