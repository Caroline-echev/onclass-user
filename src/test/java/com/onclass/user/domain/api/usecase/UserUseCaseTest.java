package com.onclass.user.domain.api.usecase;

import com.onclass.user.data.UserData;
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
        // GIVEN
        User user = userData.createUser();

        // When
        when(userPersistencePort.registerUser(user)).thenReturn(user);
        User result = userUseCase.registerUser(user);

        // Then
        assertEquals(user, result);
        verify(userPersistencePort).registerUser(user);
    }
}