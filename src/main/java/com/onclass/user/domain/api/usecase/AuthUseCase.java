package com.onclass.user.domain.api.usecase;

import com.onclass.user.domain.api.IAuthServicePort;
import com.onclass.user.domain.api.IUserServicePort;
import com.onclass.user.domain.model.Auth;
import com.onclass.user.domain.model.Token;
import com.onclass.user.domain.model.User;
import com.onclass.user.domain.spi.ITokenPort;

import java.util.Optional;

public class AuthUseCase implements IAuthServicePort {
    private final IUserServicePort userServicePort;
    private final ITokenPort tokenPort;

    public AuthUseCase(IUserServicePort userServicePort, ITokenPort tokenPort) {
        this.userServicePort = userServicePort;
        this.tokenPort = tokenPort;
    }

    @Override
    public String login(Auth auth) {
        User user = userServicePort.getUserByEmail(auth.getEmail());
        return tokenPort.getToken(user);
    }

    @Override
    public String registerAdmin(User user) {
        return tokenPort.getToken(user);

    }
}
