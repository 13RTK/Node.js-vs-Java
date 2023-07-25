package com.alex.util;

import com.alex.entity.User;

import java.io.IOException;
import java.sql.*;

public final class JDBCUtil {
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static final Connection CONNECTION;

    static {
        try {
            DRIVER = PropsUtil.getDbProps("db.mysql.driver");
            URL = PropsUtil.getDbProps("db.mysql.url");
            USERNAME = PropsUtil.getDbProps("db.mysql.username");
            PASSWORD = PropsUtil.getDbProps("db.mysql.password");

            Class.forName(DRIVER);
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Could not connect to database", e);
        }
    }

    public static Connection getCONNECTION() {
        return CONNECTION;
    }

    public static PreparedStatement getPreparedStatement(String sql) {
        try {
            return CONNECTION.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Could not prepare statement", e);
        }
    }

    public static User resultToUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt("id"), resultSet.getString("account_name"), resultSet.getString("password"), resultSet.getString("account_number"));
    }
}
