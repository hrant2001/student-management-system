package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.User;
import com.ufar.studentmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.add(user);
    }

    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<User> updateUser(User user) {
        return userRepository.update(user);
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
}
