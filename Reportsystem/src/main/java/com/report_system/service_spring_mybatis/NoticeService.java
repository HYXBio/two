package com.report_system.service_spring_mybatis;

import com.report_system.entity.Notice;

import java.util.HashMap;

public interface NoticeService {

    //获取所有
    HashMap<String,Object> allNotice();

    //增加
    HashMap<String,Object> addNotice(Notice notice);

    //修改
    HashMap<String,Object> updateNotice(Notice notice);

    //删除
    HashMap<String,Object> deleteNotice(int id, int user_id);

}
