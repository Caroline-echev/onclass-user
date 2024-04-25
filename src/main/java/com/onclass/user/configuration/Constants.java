package com.onclass.user.configuration;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }
    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";

    public static final String USER_EMAIL_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The user email you want to create already exists";
    public static final String USER_DOCUMENT_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The user document you want to create already exists";
    public static final String USER_NOT_FOUND_EMAIL_EXCEPTION_MESSAGE = "User not found. Invalid email ";
    public static final String INVALID_PASSWORD_EXCEPTION_MESSAGE = "User not found. Invalid password";
    public  static final String ADMIN_ALREADY_EXISTS_EXCEPTION_MESSAGE = "A registered administrator already exists";
    public static  final String ROLE_ADMIN ="ADMIN";
    public static  final String ROLE_TUTOR ="TUTOR";
    public static  final String ROLE_STUDENT ="STUDENT";
   }
