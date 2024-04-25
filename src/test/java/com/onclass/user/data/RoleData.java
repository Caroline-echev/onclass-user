package com.onclass.user.data;

import com.onclass.user.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.onclass.user.adapters.driving.http.dto.response.role.RoleResponse;
import com.onclass.user.domain.model.Role;

public class RoleData {
    public static Role roleAdmin(){
        return  new Role(1L, "ADMIN", "Description Admin");
    }
    public static RoleEntity roleAdminEntity(){
        return  new RoleEntity(1L, "ADMIN", "Description Admin");
    }
    public static Role roleTutor(){
        return  new Role(2L, "TUTOR", "Description Admin");
    }

    public static RoleEntity roleTutorEntity(){
        return  new RoleEntity(2L, "TUTOR", "Description Admin");
    }

    public static Role roleStudent(){
        return  new Role(3L, "STUDENT", "Description Admin");
    }

    public static RoleEntity roleStudentEntity(){
        return  new RoleEntity(3L, "STUDENT", "Description Admin");
    }
    public static RoleResponse roleAdminResponse(){
        return new RoleResponse( "ADMIN");

    }
    public static RoleResponse roleTutorResponse(){
        return new RoleResponse( "TUTOR");

    }
}
