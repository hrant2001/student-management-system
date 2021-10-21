package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface UserService {
    @Transactional
    User addUser(User user);

    List<User> findUsers();

    User findUserById(Integer id);

    Optional<User> findUserByUsername(String username);

    @Transactional
    User updateUser(User user);

    @Transactional
    void deleteUserById(Integer id);
}
