package com.onclass.user.domain.spi;

import com.onclass.user.domain.model.User;

public interface IUserPersistencePort {
    void registerUser(User user);

    User getUserByEmail(String email);
}
