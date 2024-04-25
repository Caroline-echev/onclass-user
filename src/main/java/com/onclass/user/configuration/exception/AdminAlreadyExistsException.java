package com.onclass.user.configuration.exception;

public class AdminAlreadyExistsException extends  RuntimeException {
    public AdminAlreadyExistsException(String message) {
        super(message);
    }
}
