package com.ufar.studentmanagementsystem.exception.not_found_exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
