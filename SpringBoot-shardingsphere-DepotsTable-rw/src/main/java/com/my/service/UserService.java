package com.my.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.entity.User;
import com.my.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public List<User> selectAll() {
		List<User> users = userMapper.selectAll();
		return users;
	}

	public Object insertForeach(List<User> userList) {
		for (User user : userList) {
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			user.setStatus(0);
		}
		// 批量插入数据
		userMapper.insertForeach(userList);
		return "保存成功";
	}

	public int deleteByPrimaryKey(Long id) {
		return userMapper.deleteByPrimaryKey(id);
	}


	public int updateByPrimaryKeySelective(User record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(User record) {
		return userMapper.updateByPrimaryKey(record);
	}
	
	
	public int insertone(User record) {
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setStatus(0);
		
		return userMapper.insertone(record);
	}
}
