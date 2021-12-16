package com.ufar.studentmanagementsystem.exception;

public class NotValidException extends RuntimeException {

    private String message;

    public NotValidException() {
        super();
    }

    public NotValidException(String message) {
        super(message);
        this.message = message;
    }

    public NotValidException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
