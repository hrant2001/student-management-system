package com.ufar.studentmanagementsystem.exception.not_found_exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
