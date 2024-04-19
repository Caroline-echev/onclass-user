package com.onclass.user.adapters.driving.http.controller;

import com.onclass.user.adapters.driven.jpa.mysql.adapter.AuthAdapter;
import com.onclass.user.adapters.driven.jpa.mysql.mapper.IAuthMapper;
import com.onclass.user.adapters.driving.http.dto.request.AddUserRequest;
import com.onclass.user.adapters.driving.http.dto.request.AuthRegisterRequest;
import com.onclass.user.adapters.driving.http.dto.request.LoginRequest;
import com.onclass.user.adapters.driving.http.dto.response.auth.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class  AuthRestControllerAdapter {

    private  final IAuthMapper authMapper;
    private final AuthAdapter authAdapter;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authAdapter.login(authMapper.toAuth(request)));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<AuthResponse> registerAdmin(@RequestBody AddUserRequest request)
    {
        return ResponseEntity.ok(authAdapter.registerAdmin(authMapper.toUser(request)));
    }
}
