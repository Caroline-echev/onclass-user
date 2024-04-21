package com.onclass.user.adapters.driving.http.controller;

import com.onclass.user.adapters.driven.jpa.mysql.adapter.AuthAdapter;
import com.onclass.user.adapters.driven.jpa.mysql.mapper.IAuthMapper;
import com.onclass.user.adapters.driving.http.dto.request.AddUserRequest;
import com.onclass.user.adapters.driving.http.dto.request.LoginRequest;
import com.onclass.user.adapters.driving.http.dto.response.auth.AuthResponse;
import com.onclass.user.data.AuthData;
import com.onclass.user.data.UserData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthRestControllerAdapterTest {

    @Mock
    private IAuthMapper authMapper;

    @Mock
    private AuthAdapter authAdapter;

    @InjectMocks
    private AuthRestControllerAdapter authControllerAdapter;

    private static final AuthData authData = new AuthData();
    private static final UserData userData = new UserData();
    @Test
    void testLogin() {
        LoginRequest request = authData.createLoginRequest();
        AuthResponse expectedResponse = new AuthResponse(authData.createToken());

        when(authMapper.toAuth(request)).thenReturn(authData.createAuth());
        when(authAdapter.login(any())).thenReturn(expectedResponse);

        AuthRestControllerAdapter authController = new AuthRestControllerAdapter(authMapper, authAdapter);
        ResponseEntity<AuthResponse> responseEntity = authController.login(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
   void testRegisterAdmin() {
        AddUserRequest request = authData.createAddUserRequest();
        AuthResponse expectedResponse = authData.createAuthResponse();

        when(authMapper.toUser(request)).thenReturn(userData.createUser());
        when(authAdapter.registerAdmin(any())).thenReturn(expectedResponse);

        AuthRestControllerAdapter authController = new AuthRestControllerAdapter(authMapper, authAdapter);
        ResponseEntity<AuthResponse> responseEntity = authController.registerAdmin(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
}
