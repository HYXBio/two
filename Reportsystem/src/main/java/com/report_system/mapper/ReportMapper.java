package com.report_system.mapper;

import com.report_system.entity.MyDatePara;
import com.report_system.entity.Report;

import java.util.Date;
import java.util.List;

public interface ReportMapper {
    //获取所有信息
    List<Report> getAllReport();

    //根据id查找信息
    Report getReportById(int id);

    //根据用户id查找信息
    List<Report> getReportByUserId(int user_id);

    //根据标题内容查找信息
    List<Report> getReportByTitle(String title);

    //根据信息内容查询
    List<Report> getReportBySummary(String summay);

    //根据日期查找信息
    List<Report> getReportByDate(MyDatePara myDatePara);

    //添加信息
    int addReport(Report report);

    //删除信息
    int deleteReport(int id);

    //修改信息
    int updateReport(Report report);

    //审核
    int checkReport(Report report);
}
