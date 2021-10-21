package com.ufar.studentmanagementsystem.service.impl;

import com.ufar.studentmanagementsystem.exception.already_exists_exception.UserAlreadyExistsException;
import com.ufar.studentmanagementsystem.exception.invalid_exception.InvalidUserException;
import com.ufar.studentmanagementsystem.exception.not_found_exception.UserNotFoundException;
import com.ufar.studentmanagementsystem.model.User;
import com.ufar.studentmanagementsystem.repository.UserRepository;
import com.ufar.studentmanagementsystem.service.UserService;
import com.ufar.studentmanagementsystem.utils.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User addUser(User user) {
        if (!UserValidation.IsValid(user)) {
            //logging The user is not valid
            throw new InvalidUserException("The user is not valid");
        }

        User existingUser = findUserByUsername(user.getUserName()).orElse(null);

        if (existingUser != null) {
            //logging "The user already exists with username " + user.getUserName()
            throw new UserAlreadyExistsException("The user already exists with username " + user.getUserName());
        }
        return userRepository.add(user);
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            //logging "The user with id " + id + " is not found"
            throw new UserNotFoundException("The user with id " + id + " is not found");
        }
        return user;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            //logging "The user with username " + username + " is not found"
            throw new UserNotFoundException("The user with username " + username + " is not found");
        }
        return Optional.of(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        if (!UserValidation.IsValid(user) || user.getId() <= 0 || user.getId() == null) {
            //logging The user is not valid
            throw new InvalidUserException("The user is not valid");
        }

        User updatingUser = userRepository.findById(user.getId()).orElse(null);

        if (updatingUser == null) {
            //logging "The user with id " + user.getId() + " is not found"
            throw new UserNotFoundException("The user with id " + user.getId() + " is not found");
        }
        return userRepository.update(user).get();
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        User updatingUser = userRepository.findById(id).orElse(null);
        if (updatingUser == null) {
            //logging "The user with id " + id + " is not found"
            throw new UserNotFoundException("The user with id " + id + " is not found");
        }
        userRepository.deleteById(id);
    }
}
