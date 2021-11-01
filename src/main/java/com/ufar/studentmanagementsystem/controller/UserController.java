package com.ufar.studentmanagementsystem.controller;

import com.ufar.studentmanagementsystem.model.User;
import com.ufar.studentmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        return new ResponseEntity<>(userService.addUser(newUser), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> findUsers() {
        List<User> users = userService.findUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User updatedUser) {
        return new ResponseEntity<>(userService.updateUser(updatedUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
