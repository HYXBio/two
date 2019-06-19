package com.report_system.service_spring_mybatis.impl;

import com.report_system.entity.MyDatePara;
import com.report_system.entity.Report;
import com.report_system.mapper.ReportMapper;
import com.report_system.service_spring_mybatis.ReportService;
import com.report_system.utils.GetMyBatisSession;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class ReportServiceImpl implements ReportService {
    SqlSessionFactory sqlSessionFactory = GetMyBatisSession.getSqlSessionFactory();
    @Autowired
    private ReportMapper reportMapper;
    /**
     * 获取所有信息
     * @return
     */
    @Override
    public HashMap<String, Object> getAllReport() {
        HashMap<String,Object> result = new HashMap<>();    //结果返回存放
        List<Report> allReport = reportMapper.getAllReport();  //获取所有信息
        result.put("total",allReport.size());
        result.put("rows",allReport);
        return result;
    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @Override
    public HashMap<String, Object> deleteReport(int id,int user_id) {
        HashMap<String,Object> result = new HashMap<>();    //结果返回存放
        Report reportById = reportMapper.getReportById(id);    //查找删除信息
        if(reportById ==null){
            result.put("code",1);
            result.put("msg","信息不存在");
        }else if(reportById.getUser_id()!=user_id){
            result.put("code",2);
            result.put("msg","不能删除他人上传的信息");
        } else if(reportById.getStatus().equals("审核通过")){
            result.put("code",3);
            result.put("msg","不能删除已经审核的信息");
        } else {
            reportMapper.deleteReport(id);
            result.put("code",0);
            result.put("msg","删除成功");
        }
        return result;
    }

    /**
     * 增加信息
     * @param report
     * @return
     */
    @Override
    public HashMap<String, Object> addReport(Report report) {
        report.setStatus("未审核");
        int i = reportMapper.addReport(report);
        HashMap<String,Object> result = new HashMap<>();    //结果返回存放
        if(i!=0){
            result.put("code",0);                               //存入返回信息
            result.put("msg","增加成功");
        } else {
            result.put("code",1);                               //存入返回信息
            result.put("msg","增加失败");
        }
        return result;
    }

    /**
     * 更新信息
     * @param report
     * @param user_id
     * @return
     */
    @Override
    public HashMap<String, Object> updateReport(Report report,int user_id) {
        HashMap<String,Object> result = new HashMap<>();    //结果返回存放
        if(report.getUser_id()!=user_id){
            result.put("code",1);                               //存入返回信息
            result.put("msg","不能修改他人上传信息");
        } else {
            int i = reportMapper.updateReport(report);
            result.put("code",0);                               //存入返回信息
            result.put("msg","修改成功");
        }
        return result;
    }

    /**
     * 查询信息通过标题
     * @param title
     * @return
     */
    @Override
    public HashMap<String, Object> getReportByTitele(String title) {
        List<Report> reportByTitele = reportMapper.getReportByTitle("%"+title+"%");
        HashMap<String,Object> result = new HashMap<>();    //结果返回存放
        if(reportByTitele.size()==0){
            result.put("code",1);                               //存入返回信息
            result.put("msg","查询结果为空");
        } else {
            result.put("total",reportByTitele.size());
            result.put("rows",reportByTitele);
        }
        return result;
    }

    /**
     * 查询信息通过信息内容
     * @param summary
     * @return
     */
    @Override
    public HashMap<String, Object> getReportBySummary(String summary) {
        List<Report> reportBySummary = reportMapper.getReportBySummary("%"+summary+"%");
        HashMap<String,Object> result = new HashMap<>();    //结果返回存放
        if(reportBySummary.size()==0){
            result.put("code",1);                               //存入返回信息
            result.put("msg","查询结果为空");
        } else {
            result.put("total",reportBySummary.size());
            result.put("rows",reportBySummary);
        }
        return result;
    }

    /**
     * 查询信息通过时间
     * @param begin
     * @param end
     * @return
     */
    @Override
    public HashMap<String, Object> getReportByDate(Date begin, Date end) {
        MyDatePara myDatePara = new MyDatePara(begin,end);
        List<Report> reportByDate = reportMapper.getReportByDate(myDatePara);
        HashMap<String,Object> result = new HashMap<>();    //结果返回存放
        if(reportByDate==null||reportByDate.size()==0){
            result.put("code",1);                               //存入返回信息
            result.put("msg","查询结果为空");
        } else {
            result.put("total",reportByDate.size());
            result.put("rows",reportByDate);
        }
        return result;
    }

    /**
     * 查询用户上传信息
     * @param user_id
     * @return
     */
    @Override
    public HashMap<String, Object> getReportByUserId(int user_id) {
        List<Report> reportByUserId = reportMapper.getReportByUserId(user_id);
        HashMap<String,Object> result = new HashMap<>();    //结果返回存放
        if(reportByUserId==null||reportByUserId.size()==0){
            result.put("code",1);                               //存入返回信息
            result.put("msg","查询结果为空");
        } else {
            result.put("total",reportByUserId.size());
            result.put("rows",reportByUserId);
        }
        return result;
    }


    /**
     * 审核
     * @param id
     * @return
     */
    @Override
    public HashMap<String, Object> checkReport(int id) {
        Report report = new Report();
        report.setId(id);
        report.setStatus("审核通过");
        reportMapper.checkReport(report);
        HashMap<String,Object> result = new HashMap<>();    //结果返回存放
        result.put("code",0);                               //存入返回信息
        result.put("msg","审核成功");
        return result;
    }

    @Override
    public Report getReportById(Integer id) {
        Report reportById = reportMapper.getReportById(id);
        return reportById;
    }

}
