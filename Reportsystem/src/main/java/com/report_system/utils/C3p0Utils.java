package com.report_system.utils;



import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class C3p0Utils {

    private static Connection connection;

    static {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        try {
            connection= comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
