package com.onclass.user.data;

import com.onclass.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.onclass.user.adapters.driving.http.dto.request.AddUserRequest;
import com.onclass.user.domain.model.User;

public class UserData {

    static  RoleData roleData = new RoleData();

    public final static String EMAIL = "harry@email.com";
    public final static String PASSWORD = "Password123";
    public static User createUser() {
        return new User(1L, "123", "Harry",  "Styles",  "4321",
                "harry@email.com", "Password123");
    }

    public static UserEntity createUserEntityAdmin() {
        return new UserEntity(1L ,"123", "Harry",  "Styles",  "4321",
                "harry@email.com", "Password123", roleData.roleAdminEntity());
    }
    public static UserEntity createUserEntityTutor() {
        return new UserEntity(1L ,"123", "Harry",  "Styles",  "4321",
                "harry@email.com", "Password123", roleData.roleTutorEntity());
    }
    public static UserEntity createUserEntityStudent() {
        return new UserEntity(1L ,"123", "Harry",  "Styles",  "4321",
                "harry@email.com", "Password123", roleData.roleStudentEntity());
    }



    public static AddUserRequest createUserRequest() {
        return new AddUserRequest("123", "Harry",  "Styles",  "4321",
                "harry@email.com", "Password123");
    }

}
