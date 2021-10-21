package com.ufar.studentmanagementsystem.exception.already_exists_exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
