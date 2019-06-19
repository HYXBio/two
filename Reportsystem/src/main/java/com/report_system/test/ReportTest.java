package com.report_system.test;

import com.report_system.entity.MyDatePara;
import com.report_system.entity.Notice;
import com.report_system.entity.Report;
import com.report_system.mapper.NoticeMapper;
import com.report_system.mapper.ReportMapper;
import com.report_system.utils.GetMyBatisSession;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReportTest {
    GetMyBatisSession getMyBatisSession = new GetMyBatisSession();
    SqlSessionFactory sqlSessionFactory = getMyBatisSession.getSqlSessionFactory();

    /**
     * 获取所有测试
     * @throws IOException
     */
    @Test
    public void fun0() throws IOException, ParseException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ReportMapper reportMapper = sqlSession.getMapper( ReportMapper.class );
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2019-05-03");
        Date date2 = simpleDateFormat.parse("2019-04-03");
        MyDatePara myDatePara = new MyDatePara(date2,date);
        List<Report> reportByDate = reportMapper.getReportByDate(myDatePara);
        for (Report n : reportByDate){
            System.out.println(n);
        }
        sqlSession.close();
    }

    /**
     * 根据id查找
     * @throws IOException
     */
    @Test
    public void fun1() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ReportMapper reportMapper = sqlSession.getMapper( ReportMapper.class );

        Report reportById = reportMapper.getReportById(25);

        System.out.println(reportById);
        sqlSession.close();
    }


    /**
     * 更新测试
     * @throws IOException
     */
    @Test
    public void fun2() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ReportMapper reportMapper = sqlSession.getMapper( ReportMapper.class );

        Report reportById = reportMapper.getReportById(25);
        reportById.setUrl("20190604");
        int i = reportMapper.updateReport(reportById);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 增加
     * @throws IOException
     */
    @Test
    public void fun3() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ReportMapper reportMapper = sqlSession.getMapper( ReportMapper.class );

        Date date = new Date();
        String str="我是内容";
        Report report = new Report(1,date,"新闻","我是20190604",
                str,"ll","未审核");
        int i = reportMapper.addReport(report);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除
     * @throws IOException
     */
    @Test
    public void fun4() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ReportMapper reportMapper = sqlSession.getMapper( ReportMapper.class );
        Report report = new Report();
        report.setId(46);
        report.setStatus("审核通过");
        reportMapper.checkReport(report);
        sqlSession.commit();
        sqlSession.close();
    }
}
