package com.onclass.user.adapters.driving.http.controller;

import com.onclass.user.adapters.driving.http.dto.request.AddUserRequest;
import com.onclass.user.adapters.driving.http.dto.response.user.UserResponse;
import com.onclass.user.adapters.driving.http.mapper.IUserRequestMapper;
import com.onclass.user.adapters.driving.http.mapper.IUserResponseMapper;
import com.onclass.user.data.UserData;
import com.onclass.user.domain.api.IUserServicePort;
import com.onclass.user.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRestControllerAdapterTest {

    @Mock
    private IUserServicePort userServicePort;
    @Mock
    private IUserRequestMapper userRequestMapper;
    @Mock
    private IUserResponseMapper userResponseMapper;
    @InjectMocks
    private UserRestControllerAdapter userRestControllerAdapter;

    private static final UserData userData = new UserData();
    @Test
    void testRegisterUser() {
        // Given
        AddUserRequest request = userData.createUserRequest();
        User user = userData.createUser();
        UserResponse userResponse = userData.createUserResponse();

        when(userRequestMapper.addRequestToUser(request)).thenReturn(user);
        when(userServicePort.registerUser(user)).thenReturn(user);
        when(userResponseMapper.toUserResponse(user)).thenReturn(userResponse);

        // When
        ResponseEntity<UserResponse> responseEntity = userRestControllerAdapter.registerUser(request);

        // Then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponse, responseEntity.getBody());

        verify(userRequestMapper, times(1)).addRequestToUser(request);
        verify(userServicePort, times(1)).registerUser(user);
        verify(userResponseMapper, times(1)).toUserResponse(user);
    }
}