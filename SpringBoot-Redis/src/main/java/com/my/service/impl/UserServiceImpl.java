package com.my.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.my.entity.User;
import com.my.mapper.UserMapper;
import com.my.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	private UserMapper  userMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(User record) {
		// TODO Auto-generated method stub
		return userMapper.insert(record);
	}

	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return userMapper.insertSelective(record);
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		User user = (User) redisTemplate.opsForValue().get(id);
		if(user == null) {
			System.out.println("from mysql by key:" + id);
			user = userMapper.selectByPrimaryKey(id);
			redisTemplate.opsForValue().set(id, user);
		}
		else
		{
			System.out.println("from redis by key:" + id);
		}
		return user;
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKey(record);
	}
	
	
	
	
	
}
