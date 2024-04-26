package com.onclass.user.adapters.driving.http.mapper;

import com.onclass.user.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.onclass.user.adapters.driving.http.dto.request.AddUserRequest;
import com.onclass.user.domain.model.Role;
import com.onclass.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserRequestMapper {

    @Mapping(target = "id", ignore = true)
    User addRequestToUser( AddUserRequest addUserRequest);



}
