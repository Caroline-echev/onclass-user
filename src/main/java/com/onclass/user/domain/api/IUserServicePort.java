package com.onclass.user.domain.api;

import com.onclass.user.domain.model.User;

public interface IUserServicePort {
    User registerUser(User user);
}
