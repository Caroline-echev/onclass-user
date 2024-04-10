package com.onclass.user.domain.spi;

import com.onclass.user.domain.model.User;

public interface IUserPersistencePort {
    User addUser(User user);
}
