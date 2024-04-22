package com.onclass.user.domain.api.usecase;

import com.onclass.user.data.AuthData;
import com.onclass.user.data.UserData;
import com.onclass.user.domain.api.IUserServicePort;
import com.onclass.user.domain.model.Auth;
import com.onclass.user.domain.model.User;
import com.onclass.user.domain.spi.ITokenPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthUseCaseTest {

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private ITokenPort tokenPort;

    @InjectMocks
    private AuthUseCase authUseCase;

    private static  final AuthData authData = new AuthData();
    private static  final UserData userData = new UserData();
    @Test
     void testLogin() {
        Auth auth = authData.createAuth();
        User user = userData.createUserAdmin();
        String expectedToken = authData.createToken();

        when(userServicePort.getUserByEmail(auth.getEmail())).thenReturn(user);
        when(tokenPort.getToken(user)).thenReturn(expectedToken);

        AuthUseCase authUseCase = new AuthUseCase(userServicePort, tokenPort);
        String actualToken = authUseCase.login(auth);

        assertEquals(expectedToken, actualToken);
    }

    @Test
    void testRegisterAdmin() {
        User user = userData.createUserAdmin();
        String expectedToken = authData.createToken();

        when(tokenPort.getToken(user)).thenReturn(expectedToken);

        AuthUseCase authUseCase = new AuthUseCase(userServicePort, tokenPort);
        String actualToken = authUseCase.registerAdmin(user);

        assertEquals(expectedToken, actualToken);
    }

}