package com.report_system.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;

public class GetMyBatisSession {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream( "sqlMapconfig.xml" );
        } catch (IOException e) {
            e.printStackTrace();
        }
        //通过配置创建会话工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build( inputStream );
    }



    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
