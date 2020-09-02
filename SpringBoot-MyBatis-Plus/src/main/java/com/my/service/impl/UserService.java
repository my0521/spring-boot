package com.my.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.dao.UserMapper;
import com.my.entity.User;
import com.my.service.IUserService;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

	@Override
	public boolean save(User entity) {		
		return super.save(entity);
	}

	@Override
	public List<User> getUserList() {		
		return baseMapper.selectList(Wrappers.<User>lambdaQuery());
	}

}
