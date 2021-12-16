package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User addUser(User user);

    List<User> findUsers();

    User findUserById(Integer id);

    Optional<User> findUserByUsername(String username);

    User updateUser(User user);

    void deleteUserById(Integer id);
}
