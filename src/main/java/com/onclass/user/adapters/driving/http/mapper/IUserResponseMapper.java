package com.onclass.user.adapters.driving.http.mapper;

import com.onclass.user.adapters.driving.http.dto.response.technology.UserResponse;
import com.onclass.user.domain.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserResponseMapper {
    UserResponse toUserResponse(User user);
}
