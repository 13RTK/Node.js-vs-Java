package com.alex.util;

import com.alex.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


@Component
public final class JDBCUtil {
    @Value("${spring.datasource.url}")
    private static String url;

    @Value("${spring.datasource.username}")
    private static String username;

    @Value("${spring.datasource.password}")
    private static String password;

    @Value("${spring.datasource.driver-class-name}")
    private static String driver;

    private static final Connection CONNECTION;

    static {
        try {
            Class.forName(driver);
            CONNECTION = DriverManager.getConnection(url, username, password);
            System.out.println(url + " " + username + " " + password + " " + driver);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<?> executeSQL(String sql, Class<?> tClass, String... params) {
        try {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql);
            setPreparedStatementParam(preparedStatement, params);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultsToObject(resultSet, tClass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setPreparedStatementParam(PreparedStatement statementParam, String... params) {
        for (int i = 0; i < params.length; i++) {
            try {
                statementParam.setString(i + 1, params[i]);
            } catch (SQLException e) {

                String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
                System.out.printf("Error in %s%n", methodName);
            }
        }
    }

    private static <T> List<T> resultsToObject(ResultSet set, Class<T> tClass) throws SQLException {
        List<T> resList = new LinkedList<>();

        int idx = 0;
        while (set.next()) {
            Constructor<T> allArgsConstructor = ObjectUtil.getAllArgsConstructor(tClass);
            try {
                T instance = allArgsConstructor.newInstance(set.getString(idx++));
                resList.add(instance);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        return resList;
    }
}
