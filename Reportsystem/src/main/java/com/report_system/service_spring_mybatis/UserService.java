package com.report_system.service_spring_mybatis;

import com.report_system.entity.User;

import java.util.HashMap;

public interface UserService {
    //用户登录
    HashMap<String,Object> userLogin(String user_name, String pwd);

    //获取所有用户
     HashMap<String,Object> getAllUser();

     //更新用户信息
    HashMap<String,Object> updateUser(User user);

    //增加用户
    HashMap<String,Object> addUser(User user);

    //删除用户
    HashMap<String,Object> deleteUser(int id);
}
