package com.ufar.studentmanagementsystem.exception;

public class AlreadyExistsException extends RuntimeException {

    private String message;

    public AlreadyExistsException() {
        super();
    }

    public AlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }

    public AlreadyExistsException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
