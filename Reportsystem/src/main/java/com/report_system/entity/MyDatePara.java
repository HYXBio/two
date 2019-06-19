package com.report_system.entity;

import java.util.Date;

public class MyDatePara {
    private Date beginDate;
    private Date endDate;

    public MyDatePara() {
    }

    public MyDatePara(Date beginDate, Date endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
