package com.my.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.my.entity.User;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectAll();

	void insertForeach(List<User> userList);
	
	int insertone(User record);
}