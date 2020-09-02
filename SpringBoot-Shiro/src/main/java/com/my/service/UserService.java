package com.my.service;


import com.my.po.UserBean;

public interface UserService {

    UserBean findByName(String name);
}
