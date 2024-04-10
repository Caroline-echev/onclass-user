package com.onclass.user.adapters.driving.http.mapper;

import com.onclass.user.adapters.driving.http.dto.request.AddUserRequest;
import com.onclass.user.domain.model.User;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper {
    @Mapping(target = "id", ignore = true)
    User addRequestToUser(@Valid AddUserRequest addUserRequest);

}
