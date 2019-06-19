package com.report_system.controller;

import com.report_system.entity.User;
import com.report_system.service_spring_mybatis.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param user_name
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/userLogin.action")
    @ResponseBody
    public HashMap userLogin(String user_name, String password, HttpServletRequest request){
        HashMap<String, Object> result = userService.userLogin(user_name, password);
        User user = (User) result.get("data");
        if(user!=null){
            request.getSession().setAttribute("id", user.getId());
            request.getSession().setAttribute("user_name", user_name);
        }

        return result;
    }

    /**
     *用户退出
     */
    @RequestMapping(value = "/user/userExit.action")
    @ResponseBody
    public HashMap userExit(HttpServletRequest request){
        HashMap<String, Object> result = new HashMap<>();
        result.put("msg","退出成功");
        result.put("code",0);
        request.getSession().removeAttribute("user_name");
        request.getSession().removeAttribute("id");
        return result;
    }

    /**
     *获取所有用户
     */
    @RequestMapping(value = "/user/getAllUser.action")
    @ResponseBody
    public HashMap getAllUser(){
        HashMap<String, Object> result = new HashMap<>();
        result = userService.getAllUser();
        return result;
    }

    /**
     *删除用户
     */
    @RequestMapping(value = "/user/deleteUserById.action")
    @ResponseBody
    public HashMap deleteUserById(Integer id){
        HashMap<String, Object> result = new HashMap<>();
        result = userService.deleteUser(id);
        return result;
    }

    /**
     *更新或插入用户
     */
    @RequestMapping(value = "/user/addOrUpdateUser.action")
    @ResponseBody
    public HashMap addOrUpdateUser(User user){
        HashMap<String, Object> result = new HashMap<>();
        System.out.println(user);
        if(user.getId()==null){
            user.setType(1);
            result = userService.addUser(user);
        } else {
            result= userService.updateUser(user);
        }
        return result;
    }

}
