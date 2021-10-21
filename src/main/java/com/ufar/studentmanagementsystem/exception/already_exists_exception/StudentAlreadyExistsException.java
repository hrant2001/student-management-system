package com.ufar.studentmanagementsystem.exception.already_exists_exception;

public class StudentAlreadyExistsException extends RuntimeException {
    public StudentAlreadyExistsException(String message) {
        super(message);
    }
}
