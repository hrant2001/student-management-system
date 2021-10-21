package com.ufar.studentmanagementsystem.repository;

import com.ufar.studentmanagementsystem.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    User add(User user);

    List<User> findAll();

    Optional<User> findById(Integer id);

    Optional<User> findByUsername(String username);

    Optional<User> update(User user);

    void deleteById(Integer id);
}
