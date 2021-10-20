package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findUsers();

    User addUser(User user);

    Optional<User> findUserById(Integer id);

    Optional<User> updateUser(User user);

    void deleteUserById(Integer id);
}
