package com.alex.service;

import com.alex.entity.User;
import com.alex.service.impl.UserServiceImpl;
import com.alex.util.JDBCUtil;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Before
    public void init() {
        try {
            JDBCUtil.getPreparedStatement("TRUNCATE TABLE tb_user").execute();
            userService.addUser(new User(1, "Alex", "123", "123"));
            userService.addUser(new User(2, "Alex", "123", "123"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testGetAllUser() {
        List<User> allUser = userService.getAllUser();

        assertEquals(allUser.size(), 2);
    }

    @Test
    public void testGetUserById() {
        User user = userService.getUserById(1);

        assertEquals(user.getAccountName(), "Alex");
    }

    @Test
    public void testAddUser() {
        User user = new User(20, "Alex", "123", "123");
        userService.addUser(user);

        assertEquals(userService.getAllUser().size(), 3);
    }

    @Test
    public void testUpdateUser() {
        userService.updateUser(new User(2, "John", "123", "123"));

        assertEquals(userService.getUserById(2).getAccountName(), "John");
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(2);

        assertEquals(userService.getAllUser().size(), 1);
    }
}