package com.onclass.user.domain.api.usecase;

import com.onclass.user.domain.api.IUserServicePort;
import com.onclass.user.domain.exception.NoDataFoundException;
import com.onclass.user.domain.model.User;
import com.onclass.user.domain.spi.IUserPersistencePort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder;
    public UserUseCase(IUserPersistencePort userPersistencePort, PasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void registerUser(User user) {

        encoderPassword(user);
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

    private void encoderPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

}



