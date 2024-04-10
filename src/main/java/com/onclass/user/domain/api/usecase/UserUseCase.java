package com.onclass.user.domain.api.usecase;

import com.onclass.user.domain.api.IUserServicePort;
import com.onclass.user.domain.model.User;
import com.onclass.user.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public User addUser(User user) {
        return userPersistencePort.addUser(user);
    }

}
