package com.report_system.service_spring_mybatis.impl;

import com.report_system.service_spring_mybatis.FileUploadService;
import org.apache.commons.fileupload.FileItem;

import java.util.List;

public class FileUploadServiceImpl implements FileUploadService {
    @Override
    public FileItem getUploadFileItem(List<FileItem> list) {
        for (FileItem fileItem : list) {
            if (!fileItem.isFormField()) {
                return fileItem;
            }
        }
        return null;
    }

    @Override
    public String getUploadFileName(FileItem item) {
        // 获取路径名
        String value = item.getName();
        // 索引到最后一个反斜杠
        int start = value.lastIndexOf("/");
        // 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
        String filename = value.substring(start + 1);

        return filename;
    }
}
