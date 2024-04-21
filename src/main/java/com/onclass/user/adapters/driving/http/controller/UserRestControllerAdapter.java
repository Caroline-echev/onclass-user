package com.onclass.user.adapters.driving.http.controller;


import com.onclass.user.adapters.driving.http.dto.request.AddUserRequest;
import com.onclass.user.adapters.driving.http.dto.response.user.UserResponse;
import com.onclass.user.adapters.driving.http.mapper.IUserRequestMapper;
import com.onclass.user.adapters.driving.http.mapper.IUserResponseMapper;
import com.onclass.user.domain.api.IUserServicePort;
import com.onclass.user.domain.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestControllerAdapter {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    @PostMapping("/registerUser")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody AddUserRequest request) {
        var user = userRequestMapper.addRequestToUser(request);
        userServicePort.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}