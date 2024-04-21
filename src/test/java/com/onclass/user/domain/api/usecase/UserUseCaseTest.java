package com.onclass.user.domain.api.usecase;

import com.onclass.user.data.UserData;
import com.onclass.user.domain.exception.NoDataFoundException;
import com.onclass.user.domain.model.User;
import com.onclass.user.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort ;
    @InjectMocks
    private UserUseCase userUseCase;
    private UserData userData = new UserData();

    @Test
    void testRegisterUser() {
        User user = userData.createUser();
        UserUseCase userUseCase = new UserUseCase(userPersistencePort);

        userUseCase.registerUser(user);

        verify(userPersistencePort, times(1)).encoderPassword(user);
        verify(userPersistencePort, times(1)).registerUser(user);
    }

    @Test
    void testGetUserByEmail() {

        User user =  userData.createUser();
        when(userPersistencePort.getUserByEmail(user.getEmail())).thenReturn(user);

        UserUseCase userUseCase = new UserUseCase(userPersistencePort);
        User retrievedUser = userUseCase.getUserByEmail(user.getEmail());

        assertNotNull(retrievedUser);
        assertEquals(user.getEmail(), retrievedUser.getEmail());
    }

    @Test
    void testGetUserByEmailNotFound() {
        String email = "nonexistent@example.com";
        when(userPersistencePort.getUserByEmail(email)).thenReturn(null);

        UserUseCase userUseCase = new UserUseCase(userPersistencePort);
        assertThrows(NoDataFoundException.class, () -> userUseCase.getUserByEmail(email));
    }
}