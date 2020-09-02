package com.my.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.my.dao.UserDao;
import com.my.entity.User;

@Service
public class UserService {

	@Resource
	UserDao userDao;
	
	public int addUser(User user) {
		return userDao.addUser(user);
	}	
	
	public List<User> list() {
		return userDao.list();
	}	
}
