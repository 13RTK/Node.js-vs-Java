package com.alex.alex;


import com.alex.entity.User;
import com.alex.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserRepoTest {
    @Resource
    UserRepository userRepository;


    @Test
    public void test(){
//        List<User> all = userRepository.findAll();

//        Optional<User> byId = userRepository.findById(2);
//        User user = byId.orElse(new User());
//        System.out.println(user);

//        userRepository.save(new User());

        List<User> alex = userRepository.findByAccountNameContaining("Alex");
        System.out.println(alex);

    }
}
