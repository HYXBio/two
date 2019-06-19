package com.report_system.controller;

import com.report_system.entity.Report;
import com.report_system.service_spring_mybatis.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class ReportController {
    @Autowired
    private ReportService reportService;

    /**
     * 获取所有
     * @return
     */
    @RequestMapping(value = "/report/getAllReport.action")
    @ResponseBody
    public HashMap getAllNotice(){
        HashMap<String, Object> result = new HashMap<>();
        result = reportService.getAllReport();
        return result;
    }

    /**
     * 获取所有根据id
     * @return
     */
    @RequestMapping(value = "/report/getReportById.action")
    @ResponseBody
    public HashMap getReportById(HttpServletRequest request){
        HashMap<String, Object> result = new HashMap<>();
        int user_id = (int) request.getSession().getAttribute("id");
        result = reportService.getReportByUserId(user_id);
        return result;
    }

    /**
     *删除
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/report/deleteReportById.action")
    @ResponseBody
    public HashMap deleteReportById(Integer id,HttpServletRequest request){
        HashMap<String, Object> result = new HashMap<>();
        int user_id = (int) request.getSession().getAttribute("id");
        result = reportService.deleteReport(id,user_id);
        return result;
    }

    @RequestMapping(value = "/report/updateOrAddReport.action")
    @ResponseBody
    public HashMap updateOrAddReport(Report report, HttpServletRequest request){
        HashMap<String, Object> result = new HashMap<>();
        int user_id = (int) request.getSession().getAttribute("id");
        report.setUser_id(user_id);
        if(report.getId()==null){
            report.setStatus("未审核");
            result = reportService.addReport(report);
        }
        else {
            result = reportService.updateReport(report,user_id);
        }
        return result;
    }



}
