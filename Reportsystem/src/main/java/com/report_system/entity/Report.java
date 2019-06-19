package com.report_system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.soap.Text;
import java.util.Date;

public class Report {
    //审核状态 0通过， 1 未审核，2没有通过
    public static  final  int SUCCESS = 0;
    public static  final  int READY = 1;
    public static  final  int FAILED = 2;

    private Integer     id ;         //信息id
    private Integer     user_id;     //上传者id
    private Date    report_date; //上传时间
    private String  type;        //信息类型
    private String  title;       //信息标题
    private String  summary;     //信息内容
    private String  url ;        //附件链接
    private String  status;      //审核状态

    //构造函数
    public Report(){
    }

    public Report(int user_id, Date report_date, String type, String title, String summary, String url, String status) {
        this.user_id = user_id;
        this.report_date = report_date;
        this.type = type;
        this.title = title;
        this.summary = summary;
        this.url = url;
        this.status = status;
    }

    public Report(int user_id, Date report_date, String type, String title, String summary, String status) {
        this.user_id = user_id;
        this.report_date = report_date;
        this.type = type;
        this.title = title;
        this.summary = summary;
        this.status = status;
    }



    public Report(int user_id, Date report_date, String type, String title, String summary) {
        this.user_id = user_id;
        this.report_date = report_date;
        this.type = type;
        this.title = title;
        this.summary = summary;
    }

    public Report(int user_id, Date report_date, String type, String title) {
        this.user_id = user_id;
        this.report_date = report_date;
        this.type = type;
        this.title = title;
        this.summary = summary;

    }

    //get and set


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getReport_date() {
        return report_date;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", report_date=" + report_date +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", summary=" + summary +
                ", url='" + url + '\'' +
                ", status=" + status +
                '}';
    }
}
