package com.ufar.studentmanagementsystem.service;

import com.ufar.studentmanagementsystem.model.User;
import com.ufar.studentmanagementsystem.repository.UserRepository;
import com.ufar.studentmanagementsystem.repository.UserRepositoryImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findUsers() {
        LOGGER.info("Users found");
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        LOGGER.info("User added");
        return userRepository.add(user);
    }

    @Override
    public Optional<User> findUserById(Integer id) {
        LOGGER.warn("Not found with the id no: " + id);
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        LOGGER.info("User " + user + " updated");
        return userRepository.update(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        LOGGER.info("User " + id + " is deleted");
        userRepository.deleteById(id);
    }
}
