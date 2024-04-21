package com.onclass.user.domain.api.usecase;

import com.onclass.user.domain.api.IUserServicePort;
import com.onclass.user.domain.exception.NoDataFoundException;
import com.onclass.user.domain.model.User;
import com.onclass.user.domain.spi.IUserPersistencePort;


public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public void registerUser(User user) {

        userPersistencePort.encoderPassword(user);
        userPersistencePort.registerUser(user);

    }

    @Override
    public User getUserByEmail(String email) {
       User user = userPersistencePort.getUserByEmail(email);
        if (user == null) {
            throw new NoDataFoundException(email);
        }
        return user;
    }



}



