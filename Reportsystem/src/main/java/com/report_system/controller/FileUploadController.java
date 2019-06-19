package com.report_system.controller;

import com.report_system.entity.Report;
import com.report_system.service_spring_mybatis.ReportService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@Controller
public class FileUploadController {


    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/file/fileUpload.action")
    @ResponseBody
    public HashMap fileUpload(MultipartFile multipartFile,HttpServletRequest request) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        try {
            String fileName = UUID.randomUUID().toString().replace( "-", "" );
            String path = request.getServletContext().getRealPath( "/upload/" ); //C://xx/img
            //后缀名 jpg
            String extension = FilenameUtils.getExtension( multipartFile.getOriginalFilename() );
            // C://xx/img/aa.jpg  linux:/url/tomcat/webapp/AAA/13cd.png
            File file = new File( path + fileName + "." + extension );
            System.out.println(file.getPath());
            //访问路径
            System.out.println(request.getContextPath() + "/upload/" + fileName + "." + extension);
            //保存文件
            multipartFile.transferTo( file );

//            int user_id = (int) request.getSession().getAttribute("id");
//            Report reportById = reportService.getReportById(id);
//            reportById.setUrl(request.getContextPath() + "/upload/" + fileName + "." + extension);
//            reportService.updateReport(reportById,user_id);

            hashMap.put("code",0);
            hashMap.put("msg","上传成功");
            hashMap.put("data",request.getContextPath() + "/upload/" + fileName + "." + extension);
        }catch (Exception e){
            e.printStackTrace();
            hashMap.put("code",1);
            hashMap.put("msg","上传失败");
        }
        return hashMap;
    }

    @RequestMapping("/fileUpload.do")
    public @ResponseBody HashMap fileUpload(HttpServletRequest request,@RequestParam("fileUpload") MultipartFile file){
        //简单判断文件是否为空
        HashMap<String, Object> hashMap = new HashMap<>();
        if(!file.isEmpty()){
            try {
                // 文件保存路径
                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"
                        + file.getOriginalFilename();
                file.transferTo(new File(filePath));
                hashMap.put("msg","00");
                hashMap.put("coed",0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hashMap;
    }
}
