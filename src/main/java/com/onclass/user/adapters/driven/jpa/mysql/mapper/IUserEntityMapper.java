package com.onclass.user.adapters.driven.jpa.mysql.mapper;


import com.onclass.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.onclass.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserEntityMapper {
    User toModel (UserEntity userEntity);

    UserEntity toEntity (User user);

}