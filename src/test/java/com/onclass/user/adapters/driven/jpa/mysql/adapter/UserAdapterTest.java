package com.onclass.user.adapters.driven.jpa.mysql.adapter;

import com.onclass.user.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.onclass.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.onclass.user.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.onclass.user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.onclass.user.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.onclass.user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.onclass.user.configuration.Constants;
import com.onclass.user.data.RoleData;
import com.onclass.user.data.UserData;
import com.onclass.user.domain.exception.DocumentAlreadyExistsException;
import com.onclass.user.domain.exception.EmailAlreadyExistsException;
import com.onclass.user.domain.model.Role;
import com.onclass.user.domain.model.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAdapterTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IRoleRepository roleRepository;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @Mock
    private IRoleEntityMapper roleEntityMapper;

    @InjectMocks
    private UserAdapter userAdapter;

    private static final UserData userData = new UserData();
    private static final RoleData roleData = new RoleData();
    @Test
    @DisplayName("Test that the user register correctly")
    void testRegisterUser() {
        // GIVEN
        User user = UserData.createUser();
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByDocument(anyString())).thenReturn(Optional.empty());
        when(roleRepository.findById(anyLong())).thenReturn(Optional.of(RoleData.roleAdminEntity()));
        when(userEntityMapper.toEntity(any(User.class))).thenReturn(UserData.createUserEntity());
        when(roleEntityMapper.toModel(any(RoleEntity.class))).thenReturn(RoleData.roleAdmin());

        // WHEN
         userAdapter.registerUser(user);

        // THEN

        verify(userRepository).findByEmail(anyString());
        verify(userRepository).findByDocument(anyString());
        verify(roleRepository).findById(anyLong());
        verify(userEntityMapper).toEntity(any(User.class));
        verify(roleEntityMapper).toModel(any(RoleEntity.class));
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("Test that the user not register because the email already exists")
    void testRegisterUserEmailAlreadyExists() {
        // GIVEN
        User user = UserData.createUser();

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new UserEntity()));
        // WHEN  - THEN
         assertThrows(EmailAlreadyExistsException.class, () -> userAdapter.registerUser(user));
    }
    @Test
    @DisplayName("Test that the user not register because the document already exists")
    void testRegisterUserDocumentAlreadyExists() {
        // GIVEN
        User user = UserData.createUser();

        when(userRepository.findByDocument(anyString())).thenReturn(Optional.of(new UserEntity()));
        // WHEN  - THEN
        assertThrows(DocumentAlreadyExistsException.class, () -> userAdapter.registerUser(user));
    }
}

