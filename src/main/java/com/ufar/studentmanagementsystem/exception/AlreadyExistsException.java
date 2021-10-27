package com.ufar.studentmanagementsystem.exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException() {
        super();
    }

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
