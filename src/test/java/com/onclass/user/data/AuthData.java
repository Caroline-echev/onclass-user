package com.onclass.user.data;

import com.onclass.user.adapters.driving.http.dto.request.AddUserRequest;
import com.onclass.user.adapters.driving.http.dto.request.LoginRequest;
import com.onclass.user.adapters.driving.http.dto.response.auth.AuthResponse;
import com.onclass.user.domain.model.Auth;
import com.onclass.user.domain.model.User;

public class AuthData {
    public static LoginRequest createLoginRequest() {
        return new LoginRequest("harry@email.com", "Password123");
    }
    public static Auth createAuth() {
        return new Auth("harry@email.com", "Password123");
    }
    public static String createToken() {
        return  "token";
    }
    public static AuthResponse createAuthResponse() {
        return  new AuthResponse( "token");
    }

    public static AddUserRequest createAddUserRequest() {
        return new AddUserRequest( "123", "Harry",  "Styles",  "4321",
                "harry@email.com", "Password123");
    }

}
