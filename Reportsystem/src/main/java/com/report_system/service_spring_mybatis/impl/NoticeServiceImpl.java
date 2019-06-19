package com.report_system.service_spring_mybatis.impl;


import com.report_system.entity.Notice;
import com.report_system.mapper.NoticeMapper;
import com.report_system.service_spring_mybatis.NoticeService;
import com.report_system.utils.GetMyBatisSession;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;


public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    /**
     * 获取所有
     * @return
     */
    @Override
    public HashMap<String, Object> allNotice() {
        HashMap<String,Object> result = new HashMap<>();    //存放返回结果
        List<Notice> allNotice = noticeMapper.getAllNotice();
        result.put("total",allNotice.size());
        result.put("rows",allNotice);
        return result;
    }

    /**
     * 新增
     * @param notice
     * @return
     */
    @Override
    public HashMap<String, Object> addNotice(Notice notice) {
        HashMap<String,Object> result = new HashMap<>();    //存放返回结果
        if(notice==null){
            result.put("code",1);
            result.put("msg","上传失败");
        } else {
            noticeMapper.addNotice(notice);
            result.put("code", 0);
            result.put("msg", "上传成功");
        }
        return result;
    }

    /**
     * 修改
     * @param notice
     * @return
     */
    @Override
    public HashMap<String, Object> updateNotice(Notice notice) {
        HashMap<String,Object> result = new HashMap<>();    //存放返回结果
        int i = noticeMapper.updateNotice(notice);//更新
        result.put("code",0);
        result.put("msg","修改成功");
        return result;
    }

    /**
     * 删除
     * @param id
     * @param user_id
     * @return
     */
    @Override
    public HashMap<String, Object> deleteNotice(int id, int user_id) {
        HashMap<String,Object> result = new HashMap<>();    //存放返回结果
        Notice noticetById = noticeMapper.getNoticetById(id);
        if(noticetById.getUser_id()!=user_id){
            result.put("code",1);
            result.put("msg","不能删除，权限不够");
        }
        else {
            noticeMapper.deleteNotice(id);
            result.put("code",0);
            result.put("msg","删除成功");
        }
        return result;
    }
}
