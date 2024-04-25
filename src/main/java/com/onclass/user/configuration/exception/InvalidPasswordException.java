package com.onclass.user.configuration.exception;

public class InvalidPasswordException extends  RuntimeException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
