package com.onclass.user.adapters.driving.http.dto.response.technology;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponse {

    private final Long id;
    private final String document;
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String email;
    private final Long roleId;

}
