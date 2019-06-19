package com.report_system.test;

import com.report_system.entity.Notice;
import com.report_system.mapper.NoticeMapper;
import com.report_system.mapper.UserMapper;
import com.report_system.utils.GetMyBatisSession;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoticeTest {

    GetMyBatisSession getMyBatisSession = new GetMyBatisSession();
    SqlSessionFactory sqlSessionFactory = getMyBatisSession.getSqlSessionFactory();

    /**
     * 获取所有公告测试
     * @throws IOException
     */
    @Test
    public void fun0() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        NoticeMapper noticeMapper = sqlSession.getMapper( NoticeMapper.class );

        List<Notice> allNotice = noticeMapper.getAllNotice();

        for (Notice n : allNotice){
            System.out.println(n);
        }
        sqlSession.close();
    }

    /**
     * 获取单个公告
     * @throws IOException
     */
    @Test
    public void fun1() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        NoticeMapper noticeMapper = sqlSession.getMapper( NoticeMapper.class );
        Notice noticetById = noticeMapper.getNoticetById(2);
        System.out.println(noticetById);
//
//        int i = noticeMapper.deleteNotice(2);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void fun2() throws IOException, ParseException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        NoticeMapper noticeMapper = sqlSession.getMapper( NoticeMapper.class );
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2019-05-03");
        Notice notice = new Notice("今天放假", date,1, "今天放假啦！！！！！");
        noticeMapper.addNotice(notice);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void fun3() throws IOException, ParseException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        NoticeMapper noticeMapper = sqlSession.getMapper( NoticeMapper.class );
        Notice noticetById = noticeMapper.getNoticetById(4);
        noticetById.setUrl("cess");
        int i = noticeMapper.updateNotice(noticetById);
        sqlSession.commit();
        sqlSession.close();
    }
}
