package com.ufar.studentmanagementsystem.exception;

public class NotFoundException extends RuntimeException {

    private String message;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public NotFoundException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
