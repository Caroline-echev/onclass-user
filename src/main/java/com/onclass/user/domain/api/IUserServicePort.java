package com.onclass.user.domain.api;

import com.onclass.user.domain.model.User;

import java.util.Optional;

public interface IUserServicePort {
    void registerUser(User user);
    User getUserByEmail(String email);
}
