package com.alex.util;

import com.alex.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JDBCUtilTest {
    @Test
    void executeSQL() {
        List<?> objects = JDBCUtil.executeSQL("select * from user where id=?", User.class, "1");

        for (Object object : objects) {
            assert object instanceof User : "Error converting object";
            System.out.println((User) object);
        }
    }
}