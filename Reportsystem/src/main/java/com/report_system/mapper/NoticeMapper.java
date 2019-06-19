package com.report_system.mapper;

import com.report_system.entity.Notice;

import java.util.List;

public interface NoticeMapper {
    //获取所有公告
    List<Notice> getAllNotice();

    //查找根据id
    Notice getNoticetById(int id);

    //增加
    int addNotice(Notice notice);

    //删除
    int deleteNotice(int id);

    //修改
    int updateNotice(Notice notice);
}
