package com.ufar.studentmanagementsystem.exception.invalid_exception;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String message) {
        super(message);
    }
}
