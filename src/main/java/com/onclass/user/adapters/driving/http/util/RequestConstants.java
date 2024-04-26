package com.onclass.user.adapters.driving.http.util;

public final class RequestConstants {

    private RequestConstants() {
        throw new IllegalStateException("Utility class");
    }
    public static final String NUMERIC_PATTERN ="^\\d+$" ;
    public static final String FIELD_EMPTY_MESSAGE = "Field cannot be empty";
    public static final String FIELD_CONTAINS_ONLY_NUMBERS_MESSAGE = "The field must contain only numbers";
    public static final String FIELD_EMAIL_INVALID_FORMAT_MESSAGE = "The email has an invalid format, The email is longer than 50 characters";
    public static final String  PASSWORD_PATTERN = "^[a-zA-Z0-9]+$";
    public static final String FIELD_PASSWORD_INVALID_FORMAT_MESSAGE = "The password must contain only numbers and letters, should have from 8 to 15 characters";
    public static final String FIELD_MAX_LENGTH_MESSAGE = "The field is longer than 50 characters";
    public static final String FIELD_PHONE_MAX_LENGTH_MESSAGE = "The phone is longer than 10 characters";
    public static final String FIELD_DOCUMENT_MAX_LENGTH_MESSAGE = "The document is longer than 15 characters";



}