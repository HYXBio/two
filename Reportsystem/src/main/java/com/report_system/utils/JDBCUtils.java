package com.report_system.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Iterator;
import java.util.Properties;

public class JDBCUtils {
    private static final String URL ;
    private static final String USERNAME ;
    private static final String PASSWORD ;
    static{
        String [] parp = null;
        try {
            parp = PropRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
        URL = parp[0];
        USERNAME = parp[1];
        PASSWORD=parp[2];
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 创建连接
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return conn;
    }
    public static void close(Connection co , Statement state, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(state !=null){
            try {
                state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(co !=null){
            try {
                co.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void close(Connection co , Statement state){
        if(state !=null){
            try {
                state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(co !=null){
            try {
                co.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取配置文件
     * @return
     * @throws IOException
     */
    public static String [] PropRead()throws IOException {
        String[] para = new String[3];
        int i = 0;
        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream("E:\\java-web-re\\src\\com\\utils\\jdbc.properties");
        InputStream in = new BufferedInputStream(fileInputStream);
        prop.load(in);
        Iterator<String> it = prop.stringPropertyNames().iterator();
        while (it.hasNext()) {
            para[i] = prop.getProperty(it.next());
            i++;
        }
        in.close();
        return para;
    }

}
