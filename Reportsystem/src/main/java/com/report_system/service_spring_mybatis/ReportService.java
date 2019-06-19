package com.report_system.service_spring_mybatis;

import com.report_system.entity.Report;

import java.util.Date;
import java.util.HashMap;

public interface ReportService {
    //获取所有信息
    HashMap<String,Object> getAllReport();

    //删除信息
    HashMap<String,Object> deleteReport(int id, int user_id);

    //增加信息
    HashMap<String,Object> addReport(Report report);

    //修改信息
    HashMap<String,Object> updateReport(Report report, int user_id);

    //根据标题查找信息，以什么开头0 包含什么1
    HashMap<String,Object> getReportByTitele(String title);

    //根据信息内容查询
    HashMap<String,Object> getReportBySummary(String summary);

    //根据日期查找信息
    HashMap<String,Object> getReportByDate(Date begin, Date end);

    //根据用户信息获取信息

    HashMap<String,Object> getReportByUserId(int user_id);

    //审核
    HashMap<String,Object> checkReport(int id);

    //获取报告根据id
    Report getReportById(Integer id);

}
