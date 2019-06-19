package com.report_system.controller;

import com.report_system.entity.Notice;
import com.report_system.service_spring_mybatis.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 所有公告
     * @return
     */
    @RequestMapping(value = "/notice/getAllNotice.action")
    @ResponseBody
    public HashMap getAllNotice(){
        HashMap<String, Object> result = new HashMap<>();
        result = noticeService.allNotice();

        return result;
    }

    /**
     * 删除公告
     * @param noticeId
     * @param request
     * @return
     */
    @RequestMapping(value = "/notice/deleteNotice.action")
    @ResponseBody
    public HashMap deleteNotice(Integer noticeId, HttpServletRequest request){
        HashMap<String, Object> result = new HashMap<>();
        int user_id = (int) request.getSession().getAttribute("id");
        result = noticeService.deleteNotice(noticeId,user_id);
        return result;
    }

    /**
     * 增加修改公告
     * @param notice
     * @param request
     * @return
     */
    @RequestMapping(value = "/notice/addOrUpdateNotice.action")
    @ResponseBody
    public HashMap addOrUpdateNotice(Notice notice, HttpServletRequest request){
        HashMap<String, Object> result = new HashMap<>();
        int user_id = (int) request.getSession().getAttribute("id");
        if(notice.getId()==null){
           notice.setUser_id(user_id);
           result= noticeService.addNotice(notice);
        }else {
            notice.setUser_id(user_id);
            result = noticeService.updateNotice(notice);
        }
        return result;
    }

}
