package com.onclass.user.data;

import com.onclass.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.onclass.user.adapters.driving.http.dto.request.AddUserRequest;
import com.onclass.user.adapters.driving.http.dto.response.user.UserResponse;
import com.onclass.user.domain.model.User;

public class UserData {

    static  RoleData roleData = new RoleData();

    public static User createUser() {
        return new User(1L, "123", "Harry",  "Styles",  "4321",
                "harry@email.com", "Password123");
    }
    public static UserEntity createUserEntity() {
        return new UserEntity(1L ,"123", "Harry",  "Styles",  "4321",
                "harry@email.com", "Password123", roleData.roleAdminEntity());
    }

    public static AddUserRequest createUserRequest() {
        return new AddUserRequest("123", "Harry",  "Styles",  "4321",
                "harry@email.com", "Password123");
    }
    public static UserResponse createUserResponse() {
        return new UserResponse(1L, "123", "Harry",  "Styles",  "4321",
                "harry@email.com", roleData.roleAdminResponse());
    }
}
