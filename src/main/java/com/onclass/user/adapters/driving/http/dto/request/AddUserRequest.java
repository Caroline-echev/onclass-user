package com.onclass.user.adapters.driving.http.dto.request;




import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class AddUserRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String document;
    private String phone;
    private String email;
    private Long roleId;
    private String password;
}
