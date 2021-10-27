package com.ufar.studentmanagementsystem.service.impl;

import com.ufar.studentmanagementsystem.exception.already_exists_exception.UserAlreadyExistsException;
import com.ufar.studentmanagementsystem.exception.invalid_exception.InvalidUserException;
import com.ufar.studentmanagementsystem.exception.not_found_exception.UserNotFoundException;
import com.ufar.studentmanagementsystem.model.User;
import com.ufar.studentmanagementsystem.repository.UserRepository;
import com.ufar.studentmanagementsystem.service.UserService;
import com.ufar.studentmanagementsystem.utils.validation.UserValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User addUser(User user) {
        if (!UserValidation.IsValid(user)) {
            LOGGER.warn("The user " + user + " is not valid");
            throw new InvalidUserException("The user is not valid");
        }

        User existingUser = findUserByUsername(user.getUserName()).orElse(null);

        if (existingUser != null) {
            LOGGER.warn("The user " + user.getUserName() + " already exists");
            throw new UserAlreadyExistsException("The user already exists with username " + user.getUserName());
        }
        LOGGER.info("The user " + user + " is added");
        return userRepository.add(user);
    }

    @Override
    public List<User> findUsers() {
        LOGGER.info("Users are found");
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            LOGGER.warn("The user " + id + " is not found");
            throw new UserNotFoundException("The user with id " + id + " is not found");
        }

        return user;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        LOGGER.info("User " + username + " is found");
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        if (user.getId() == null || user.getId() <= 0 || !UserValidation.IsValid(user)) {
            LOGGER.warn("Invalid user");
            throw new InvalidUserException("The user is not valid");
        }

        User updatingUser = userRepository.findById(user.getId()).orElse(null);

        if (updatingUser == null) {
            LOGGER.warn("The user " + user.getId() + " is not found");
            throw new UserNotFoundException("The user with id " + user.getId() + " is not found");
        }
        LOGGER.info("User " + user + " is updated");
        return userRepository.update(user).get();
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        User updatingUser = userRepository.findById(id).orElse(null);
        if (updatingUser == null) {
            LOGGER.warn("User no: " + id + " is not found");
            throw new UserNotFoundException("The user with id " + id + " is not found");
        }
        LOGGER.info("User " + id + " is deleted");
        userRepository.deleteById(id);
    }
}
