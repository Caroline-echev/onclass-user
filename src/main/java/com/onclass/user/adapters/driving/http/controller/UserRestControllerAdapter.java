package com.onclass.user.adapters.driving.http.controller;


import com.onclass.user.adapters.driving.http.dto.request.AddUserRequest;
import com.onclass.user.adapters.driving.http.dto.response.technology.UserResponse;
import com.onclass.user.adapters.driving.http.mapper.IUserRequestMapper;
import com.onclass.user.adapters.driving.http.mapper.IUserResponseMapper;
import com.onclass.user.domain.api.IUserServicePort;
import com.onclass.user.domain.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestControllerAdapter {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;
    @PostMapping("/addUser")
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody AddUserRequest request){
       User user = userServicePort.addUser(userRequestMapper.addRequestToUser(request));
        UserResponse response = userResponseMapper.toUserResponse(user);
        return ResponseEntity.ok(response);
    }
}