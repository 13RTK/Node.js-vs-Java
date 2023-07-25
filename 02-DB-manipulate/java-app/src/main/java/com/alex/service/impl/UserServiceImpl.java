package com.alex.service.impl;

import com.alex.entity.User;
import com.alex.service.UserService;
import com.alex.util.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserServiceImpl implements UserService {


    public List<User> getAllUser() {
        PreparedStatement preparedStatement = JDBCUtil.getPreparedStatement("SELECT * FROM tb_user");
        List<User> resList = new LinkedList<User>();

        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resList.add(JDBCUtil.resultToUser(resultSet));
            }

            return resList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserById(Integer id) {
        PreparedStatement preparedStatement = JDBCUtil.getPreparedStatement("SELECT * FROM tb_user where id=?");
        User resUser = new User();

        try {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resUser = JDBCUtil.resultToUser(resultSet);
            }

            return resUser;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User addUser(User user) {
        PreparedStatement preparedStatement = JDBCUtil.getPreparedStatement("INSERT INTO tb_user(account_name, password, account_number) VALUES(?, ?, ?)");
        try {
            preparedStatement.setString(1, user.getAccountName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getAccountNumber());

            preparedStatement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User updateUser(User user) {
        PreparedStatement preparedStatement = JDBCUtil.getPreparedStatement("UPDATE tb_user SET account_name=?, password=?, account_number=? WHERE id=?");
        try {
            preparedStatement.setString(1, user.getAccountName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getAccountNumber());
            preparedStatement.setInt(4, user.getId());

            preparedStatement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(Integer id) {
        PreparedStatement preparedStatement = JDBCUtil.getPreparedStatement("DELETE FROM tb_user WHERE id=?");
        try {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
