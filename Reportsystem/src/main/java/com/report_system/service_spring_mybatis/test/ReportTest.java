package com.report_system.service_spring_mybatis.test;

import com.report_system.entity.Report;
import com.report_system.service_spring_mybatis.ReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext.xml")
public class ReportTest {
    @Autowired
    private ReportService reportService;

    /**
     * 删除
     */
    @Test
    public void fun(){
        HashMap<String, Object> deleteReport = reportService.deleteReport(26,1);
        System.out.println(deleteReport);
    }

    /**
     * 增加
     */
    @Test
    public void fun1(){
//        public Report(int user_id, Date report_date, String type, String title, String summary) {
        Report report = new Report(1,new Date(),"测试","测试","cese");
        HashMap<String, Object> stringObjectHashMap = reportService.addReport(report);
        System.out.println(stringObjectHashMap);
    }

    /**
     * 修改测试完了，查找测试
     */
    @Test
    public void fun2() throws ParseException {

        HashMap<String, Object> stringObjectHashMap = reportService.getReportByTitele("测试");
        HashMap<String, Object> stringObjectHashMap1 = reportService.getReportBySummary("测试");
        System.out.println(stringObjectHashMap);
        System.out.println(stringObjectHashMap1);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2019-5-5");
        Date date2 = simpleDateFormat.parse("2019-6-13");
        HashMap<String, Object> reportByDate = reportService.getReportByDate(date, date2);
        System.out.println(reportByDate);

        HashMap<String, Object> reportByUserId = reportService.getReportByUserId(1);
        System.out.println(reportByUserId);
        HashMap<String, Object> stringObjectHashMap2 = reportService.checkReport(60);
        System.out.println(stringObjectHashMap2);
    }
}
