package com.my.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.my.entity.User;

@Repository
public class UserDao {

	@Resource
	JdbcTemplate jdbcTemplate;
	
	public int addUser(User user) {		
		 String sql = "INSERT INTO `User` (name,age) VALUES (?, ?)";
	     return jdbcTemplate.update(sql, new Object[] { user.getName(), user.getAge() });		
	}
	
	public List<User> list() {
		String sql = "SELECT * FROM `User`";
		return jdbcTemplate.query(sql, new  RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setAge(rs.getInt("age"));
				return user;
			}});
	}	
}
