package com.report_system.service_spring_mybatis;

import org.apache.commons.fileupload.FileItem;

import java.util.List;

public interface FileUploadService {
    //获取上传文件
    FileItem getUploadFileItem(List<FileItem> list);

    //获取上传文件名
    String getUploadFileName(FileItem item);
}
