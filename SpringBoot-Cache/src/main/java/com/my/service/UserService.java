package com.my.service;


import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.my.entity.User;


@CacheConfig(cacheNames = "users")
public interface UserService {
    // 增、改、查、删
    User addUser(User user) ;
    
    @CachePut(key ="#p0") 
    User updateUser(Integer id) ;
    
    @Cacheable(key ="#p0") 
    User selectUser(Integer id) ;
    
    @CacheEvict(key ="#p0",allEntries=true)//删除所有的缓存
    void deleteUser(Integer id);
}
