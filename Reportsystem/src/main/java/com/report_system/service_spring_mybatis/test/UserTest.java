package com.report_system.service_spring_mybatis.test;

import com.report_system.entity.User;

import com.report_system.service_spring_mybatis.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext.xml")
public class UserTest {
    /**
     * 获取所有用户
     */
    @Autowired
    private UserService userService;
    @Test
    public void fun(){
        HashMap<String, Object> allUser = userService.getAllUser();
        List<User> rows = (List<User>) allUser.get("rows");
        for (User u : rows){
            System.out.println(u);
        }
    }

    /**
     * 登录测试
     */
    @Test
    public void fun1(){
        HashMap<String, Object> result = userService.userLogin("lk0000", "123456");
        System.out.println(result);
    }

    /**
     * 登录测试
     */
    @Test
    public void fun2(){
//         public User(String user_name, String password, String department, String contact, String phone, int type) {
        User user = new User("test1","123456","diqiu","lk","12346",1);

        HashMap<String, Object> result = userService.addUser(user);
        user.setId(8);
        user.setPassword("ddddd");
        HashMap<String, Object> stringObjectHashMap = userService.updateUser(user);
        HashMap<String, Object> stringObjectHashMap1 = userService.deleteUser(8);
        System.out.println(stringObjectHashMap1);
    }
}
