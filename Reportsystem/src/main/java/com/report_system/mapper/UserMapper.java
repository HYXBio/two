package com.report_system.mapper;

import com.report_system.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    //根据id获取用户信息
    User findUserById(int id);

    //根据用户名获取用户信息
    User SearchUserByName(String name);

   //获取全部用户
    List<User> selectAllUser();

    //更新用户信息
    int upDateUser(User user);

    //插入数据
    int addUser(User user);

    //删除用户
    int deleteUserById(int id);

}
