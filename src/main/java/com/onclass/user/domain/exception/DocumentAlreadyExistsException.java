package com.onclass.user.domain.exception;

public class DocumentAlreadyExistsException extends RuntimeException {

    public DocumentAlreadyExistsException(String message) {
        super(message);
    }
}
