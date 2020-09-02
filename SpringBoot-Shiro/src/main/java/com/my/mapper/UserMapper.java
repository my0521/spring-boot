package com.my.mapper;

import com.my.po.UserBean;

public interface UserMapper {

    UserBean findByName(String name);

    UserBean findById(String id);
}