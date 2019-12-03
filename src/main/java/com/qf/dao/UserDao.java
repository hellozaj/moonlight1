package com.qf.dao;

import com.qf.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    public User findByName(String pass);
    //手机号调头像
    public int findpp(float photo);
}
