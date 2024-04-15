package com.onclass.user.adapters.driving.http.mapper;

import com.onclass.user.adapters.driving.http.dto.request.AddUserRequest;
import com.onclass.user.adapters.driving.http.dto.request.AuthRegisterRequest;
import com.onclass.user.domain.model.User;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserRequestMapper {
    @Mapping(target = "id", ignore = true)
    User addRequestToUser(@Valid AddUserRequest addUserRequest);

    @Mapping(target = "id", ignore = true)
    User authRegisterRequestToUser(@Valid AuthRegisterRequest authRegisterRequest);

}
