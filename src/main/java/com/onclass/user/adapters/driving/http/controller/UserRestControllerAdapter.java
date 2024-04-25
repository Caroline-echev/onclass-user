package com.onclass.user.adapters.driving.http.controller;


import com.onclass.user.adapters.driven.jpa.mysql.adapter.UserAdapter;
import com.onclass.user.adapters.driving.http.dto.request.AddUserRequest;
import com.onclass.user.adapters.driving.http.mapper.IUserRequestMapper;
import com.onclass.user.domain.api.IUserServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication")
@SecurityRequirement(name = "bearer-key")
public class UserRestControllerAdapter {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;

    @Operation(summary = "Register Admin")
    @ApiResponse(responseCode = "200", description = "Admin registered successfully")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PostMapping("/registerUser")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody AddUserRequest request) {
        var user = userRequestMapper.addRequestToUser(request);
        userServicePort.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }



}