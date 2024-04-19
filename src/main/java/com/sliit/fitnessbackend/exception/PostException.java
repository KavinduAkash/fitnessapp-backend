package com.sliit.fitnessbackend.exception;

public class PostException extends RuntimeException {
    private int status;
    private String message;
    public PostException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
