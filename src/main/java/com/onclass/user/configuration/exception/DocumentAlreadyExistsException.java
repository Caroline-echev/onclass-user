package com.onclass.user.configuration.exception;

public class DocumentAlreadyExistsException extends RuntimeException {

    public DocumentAlreadyExistsException(String message) {
        super(message);
    }
}
