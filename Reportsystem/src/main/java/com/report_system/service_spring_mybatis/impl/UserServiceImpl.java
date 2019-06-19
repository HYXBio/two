package com.report_system.service_spring_mybatis.impl;

import com.report_system.entity.User;
import com.report_system.mapper.UserMapper;
import com.report_system.service_spring_mybatis.UserService;
import com.report_system.utils.GetMyBatisSession;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;


public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    SqlSessionFactory sqlSessionFactory = GetMyBatisSession.getSqlSessionFactory();
    //用户登录
    @Override
    public HashMap<String, Object> userLogin(String user_name, String pwd) {
        //定义返回对象
        HashMap<String ,Object> result = new HashMap<>();
        //获取登录对象信息
        User user = userMapper.SearchUserByName(user_name);
        //判断对象是否存在
        if(user ==null){
            result.put("code",2);
            result.put("msg","用户不存在");

        }else if(user.getPassword().equals(pwd)){
            //显示必要信息
            User showUser = new User();
            showUser.setUser_name(user_name);
            showUser.setId(user.getId());
            showUser.setType(user.getType());
            //返回信息
            result.put("code",0);
            result.put("msg","登录成功");
            result.put("data",showUser);

        }else if(!user.getPassword().equals(pwd)){
            //登录失败返回信息
            result.put("code",1);
            result.put("msg","账户密码错误");
        }
        return result;
    }

    //获取所有用户
    @Override
    public HashMap<String,Object> getAllUser() {
        //定义返回对象
        HashMap<String ,Object> result = new HashMap<>();
        List<User> users = userMapper.selectAllUser();
        result.put("total",users.size());
        result.put("rows",users);
        return result;
    }

    //更新用户信息
    @Override
    public HashMap<String, Object> updateUser(User user) {
        //定义返回对象
        HashMap<String ,Object> result = new HashMap<>();
        //查找修改用户
        User old_user = userMapper.findUserById(user.getId());
        //判断用户名是否重复
        if(old_user.getUser_name().equals(user.getUser_name())&&old_user.getId()!=user.getId()){
            result.put("code",1);
            result.put("msg","用户名已经存在");
        }
        else {
            int i = userMapper.upDateUser(user);
            result.put("code",0);
            result.put("msg","修改成功");
        }
        return result;
    }

    //添加用户
    @Override
    public HashMap<String, Object> addUser(User user) {
        //定义返回对象
        HashMap<String ,Object> result = new HashMap<>();
        //设置默认值
        result.put("code",0);
        result.put("msg","增加成功");
        //获取所有用户
        List<User> users = userMapper.selectAllUser();
        //判断用户是否存在
        Integer check  = 0;
        for (User u : users){
            if(u.getUser_name().equals(user.getUser_name())){
                result.replace("code",1);
                result.replace("msg","用户名存在");
            }
        }
        if(result.get("code")==check){
            userMapper.addUser(user);
        }
        return result;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public HashMap<String, Object> deleteUser(int id) {
        HashMap<String ,Object> result = new HashMap<>();
        int i = userMapper.deleteUserById(id);
        result.put("code",0);
        result.put("msg","删除成功！");
        return result;
    }
}
