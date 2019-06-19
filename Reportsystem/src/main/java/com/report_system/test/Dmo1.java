package com.report_system.test;

import com.report_system.entity.User;
import com.report_system.mapper.UserMapper;
import com.report_system.utils.GetMyBatisSession;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Dmo1 {


    GetMyBatisSession getMyBatisSession = new GetMyBatisSession();
    SqlSessionFactory sqlSessionFactory = getMyBatisSession.getSqlSessionFactory();

    @Test
    public void fun1() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream( "sqlMapconfig.xml" );
        //通过配置创建会话工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build( inputStream );

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper( UserMapper.class );
        User user = userMapper.findUserById( 1 );
        System.out.println(user);
        User user1 = userMapper.SearchUserByName("lk0000");
        System.out.println(user1);
        sqlSession.close();

    }

    @Test
    public void fun2() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper( UserMapper.class );
        User user = userMapper.findUserById( 1 );
        user.setPhone("123456789111");
        System.out.println(user);
        int i = userMapper.upDateUser(user);

        System.out.println(i);
        User user2 = userMapper.findUserById( 1 );
        System.out.println(user2);
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void fun3() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper( UserMapper.class );
        List<User> users = userMapper.selectAllUser();
        for(User u :users){
            System.out.println(u);
        }
        sqlSession.close();
    }

    @Test
    public void fun4() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper( UserMapper.class );
        User user = new User("test","123456","test","test","15252525",1);
        int i = userMapper.addUser(user);
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void fun5() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper( UserMapper.class );

        int i = userMapper.deleteUserById(4);

        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
    }
}
