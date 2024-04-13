package com.onclass.user.configuration;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }
    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";

    public static final String USER_EMAIL_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The user email you want to create already exists";
    public static final String USER_DOCUMENT_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The user document you want to create already exists";
   }
