package com.alex.service;

import com.alex.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User getUserById(Integer id);

    User addUser(User user);

    User updateUser(User user);

    void deleteUser(Integer id);
}
