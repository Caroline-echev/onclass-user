package com.onclass.user.adapters.driven.jpa.mysql.adapter;

import com.onclass.user.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.onclass.user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.onclass.user.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.onclass.user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.onclass.user.configuration.Constants;
import com.onclass.user.configuration.exception.AdminAlreadyExistsException;
import com.onclass.user.configuration.exception.NoDataFoundException;
import com.onclass.user.data.RoleData;
import com.onclass.user.data.UserData;
import com.onclass.user.domain.model.Role;
import com.onclass.user.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserAdapter userAdapter;

    private static final UserData userData = new UserData();
    private static final RoleData roleData = new RoleData();
    @Test
    void testRegisterUser() {
        //GIVEN
        User user = UserData.createUser();
        Role role = roleData.roleAdmin();

        //WHEN
        when(roleRepository.findByName(Constants.ROLE_ADMIN)).thenReturn(Optional.of(roleData.roleAdminEntity()));
        when(roleEntityMapper.toModel(any())).thenReturn(role);

        UserAdapter userAdapter = new UserAdapter(userRepository, userEntityMapper, roleRepository, roleEntityMapper, passwordEncoder);
        userAdapter.registerUser(user);

        //THEN
        verify(userRepository, times(1)).save(any());
        assertEquals(role, user.getRole());
    }
    @Test
    void testRegisterUser_AdminAlreadyExistsException() {
        //GIVEN
        User user = UserData.createUser();
        Role role = roleData.roleAdmin();

        when(roleRepository.findByName(Constants.ROLE_ADMIN)).thenReturn(Optional.of(roleData.roleAdminEntity()));
        when(roleEntityMapper.toModel(any())).thenReturn(role);
        when(userRepository.findByRole(any())).thenReturn(Optional.of(UserData.createUserEntityAdmin()));

        //THEN
        AdminAlreadyExistsException exception = assertThrows(AdminAlreadyExistsException.class, () -> userAdapter.registerUser(user));
        assertEquals(Constants.ADMIN_ALREADY_EXISTS_EXCEPTION_MESSAGE, exception.getMessage());
    }
    @Test
     void testGetUserByEmail() {
        //GIVEN
        User user = UserData.createUser();

        //WHEN
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(UserData.createUserEntityAdmin()));
        when(userEntityMapper.toUserModel(any())).thenReturn(user);
        UserAdapter userAdapter = new UserAdapter(userRepository, userEntityMapper, roleRepository, roleEntityMapper, passwordEncoder);

        //THEN
        assertEquals(user, userAdapter.getUserByEmail(user.getEmail()));
    }

    @Test
    void testGetUserByEmailNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        UserAdapter userAdapter = new UserAdapter(userRepository, userEntityMapper, roleRepository, roleEntityMapper, passwordEncoder);
        assertThrows(NoDataFoundException.class, () -> userAdapter.getUserByEmail("nonexistent@example.com"));
    }

    @Test
    void testEncoderPassword() {
        User user = UserData.createUser();
        user.setPassword("password123");

        UserAdapter userAdapter = new UserAdapter(userRepository, userEntityMapper, roleRepository, roleEntityMapper, passwordEncoder);
        userAdapter.encoderPassword(user);

        verify(passwordEncoder, times(1)).encode("password123");
    }
    @Test
    void testRegisterUser_NoDefaultRoleFound() {
        User user = UserData.createUser();
        when(roleRepository.findByName(Constants.ROLE_ADMIN)).thenReturn(Optional.empty());

        UserAdapter userAdapter = new UserAdapter(userRepository, userEntityMapper, roleRepository, roleEntityMapper, passwordEncoder);

        assertThrows(NoDataFoundException.class, () -> userAdapter.registerUser(user));
    }
}

