package com.onclass.user.adapters.driven.jpa.mysql.mapper;

import com.onclass.user.adapters.driving.http.dto.request.AddUserRequest;
import com.onclass.user.adapters.driving.http.dto.request.AuthRegisterRequest;
import com.onclass.user.adapters.driving.http.dto.request.LoginRequest;
import com.onclass.user.domain.model.Auth;
import com.onclass.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.core.userdetails.UserDetails;
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IAuthMapper {


    Auth toAuth(LoginRequest request);

    User toUser (AddUserRequest request);
}
