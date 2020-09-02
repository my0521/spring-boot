package com.my.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.my.entity.User;

public interface IUserService extends IService<User> {

	@Override
	boolean save(User entity);

	List<User> getUserList();
}
