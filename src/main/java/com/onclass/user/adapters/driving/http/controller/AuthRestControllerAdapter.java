package com.onclass.user.adapters.driving.http.controller;

import com.onclass.user.adapters.driven.jpa.mysql.adapter.AuthAdapter;
import com.onclass.user.adapters.driven.jpa.mysql.mapper.IAuthMapper;
import com.onclass.user.adapters.driving.http.dto.request.AddUserRequest;
import com.onclass.user.adapters.driving.http.dto.request.LoginRequest;
import com.onclass.user.adapters.driving.http.dto.response.auth.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication")
@SecurityRequirement(name = "bearer-key")
public class  AuthRestControllerAdapter {

    private  final IAuthMapper authMapper;
    private final AuthAdapter authAdapter;


    @Operation(summary = "Login")
    @ApiResponse(responseCode = "200", description = "Successful login")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authAdapter.login(authMapper.toAuth(request)));
    }

    @Operation(summary = "Register a new admin")
    @ApiResponse(responseCode = "200", description = "Admin registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PostMapping("/register/admin")
    @PreAuthorize("hasAuthority('ADMIN')")

    public ResponseEntity<AuthResponse> registerAdmin(@RequestBody AddUserRequest request)
    {
        return ResponseEntity.ok(authAdapter.registerAdmin(authMapper.toUser(request)));
    }


    @Operation(summary = "Register a new tutor")
    @ApiResponse(responseCode = "200", description = "Tutor registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PostMapping("/register/tutor")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AuthResponse> registerTutor(@RequestBody AddUserRequest request)
    {
        return ResponseEntity.ok(authAdapter.registerTutor(authMapper.toUser(request)));
    }


    @Operation(summary = "Register a new student")
    @ApiResponse(responseCode = "200", description = "Student registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PostMapping("/register/student")
    @PreAuthorize("hasAuthority('ADMIN' , 'TUTOR')")
    public ResponseEntity<AuthResponse> registerStudent(@RequestBody AddUserRequest request)
    {
        return ResponseEntity.ok(authAdapter.registerStudent(authMapper.toUser(request)));
    }
}
