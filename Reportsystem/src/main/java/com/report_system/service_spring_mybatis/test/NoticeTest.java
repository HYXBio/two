package com.report_system.service_spring_mybatis.test;

import com.report_system.entity.Notice;
import com.report_system.mapper.NoticeMapper;
import com.report_system.service_spring_mybatis.NoticeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.Date;
import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext.xml")
public class NoticeTest {
    @Autowired
    private NoticeService noticeService;

    @Test
    public void fun(){
        HashMap<String, Object> stringObjectHashMap = noticeService.allNotice();
        System.out.println(stringObjectHashMap);
    }

    @Test
    public void fun1(){
        Notice notice = new Notice("今天放假",new Date(),1,"今天放假啦","sss");
//        HashMap<String, Object> stringObjectHashMap = noticeService.addNotice(notice);

        notice.setId(11);
        notice.setTitle("wostest");
        HashMap<String, Object> stringObjectHashMap = noticeService.deleteNotice(11,1);

        System.out.println(stringObjectHashMap);
    }
}
