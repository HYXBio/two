package com.report_system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Notice {

    private Integer id;
    private String title;
    private Date publishDate;
    private Integer user_id;
    private String content;
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Notice() {
    }

    public Notice(String title, Date publishDate, int user_id, String content, String url) {
        this.title = title;
        this.publishDate = publishDate;
        this.user_id = user_id;
        this.content = content;
        this.url = url;
    }
    public Notice(String title, Date publishDate, int user_id, String content) {
        this.title = title;
        this.publishDate = publishDate;
        this.user_id = user_id;
        this.content = content;

    }


    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishDate=" + publishDate +
                ", user_id=" + user_id +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
