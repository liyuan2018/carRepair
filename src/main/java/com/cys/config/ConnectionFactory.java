package com.cys.config;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by liyuan on 2018/3/16.
 */
public class ConnectionFactory {
    private static DataSource dataSource = null;

    public ConnectionFactory() {
    }

    public static Connection getDatabaseConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void setDataSource(DataSource ds) {
        dataSource = ds;
    }
}
