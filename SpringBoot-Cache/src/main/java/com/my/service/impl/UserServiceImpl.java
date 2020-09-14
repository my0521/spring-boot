package com.my.service.impl;

import com.my.entity.User;
import com.my.service.UserService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User addUser(User user) {
    	 System.out.println(user.toString());
        return user ;
    }


    @Override
    public User updateUser(Integer id) {
        User user = new User() ;
        user.setId(id);
        user.setName("smile");
        System.out.println(user.toString());
        return user;
    }


    @Override
    public User selectUser(Integer id) {
        User user = new User() ;
        user.setId(id);
        user.setName("cicadaSmile");
        System.out.println(user.toString());
        return user;
    }

    @Override
    public void deleteUser(Integer id) {
    	System.out.println("deleteUser#id=" + id);
    }
}
