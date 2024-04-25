package com.onclass.user.adapters.driven.jpa.mysql.adapter;

import com.onclass.user.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.onclass.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.onclass.user.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.onclass.user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.onclass.user.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.onclass.user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.onclass.user.adapters.driving.http.dto.response.auth.AuthResponse;
import com.onclass.user.configuration.Constants;
import com.onclass.user.configuration.exception.InvalidPasswordException;
import com.onclass.user.configuration.exception.NoDataFoundException;
import com.onclass.user.configuration.jwt.JwtService;
import com.onclass.user.data.AuthData;
import com.onclass.user.data.RoleData;
import com.onclass.user.data.UserData;
import com.onclass.user.domain.model.Auth;
import com.onclass.user.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthAdapterTest {
    @Mock
    private IUserRepository userRepository;

    @Mock
    private IRoleRepository roleRepository;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @Mock
    private IRoleEntityMapper roleEntityMapper;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthAdapter authAdapter;

    private static final UserData userData = new UserData();
    private static final RoleData roleData = new RoleData();

    private static final AuthData authData = new AuthData();

    @Test
    void LoginTest() {
        // Given
        Auth auth = authData.createAuth();
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(authData.EMAIL);
        userEntity.setPassword(authData.PASSWORD);

        when(userRepository.findByEmail(auth.getEmail())).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches(auth.getPassword(), userEntity.getPassword())).thenReturn(true);
        when(jwtService.getToken(any())).thenReturn(authData.createToken());

        // When
        AuthResponse authResponse = authAdapter.login(auth);

        // Then
        assertNotNull(authResponse);
        assertNotNull(authResponse.getToken());
        assertEquals(authData.createToken(), authResponse.getToken());
    }

    @Test
    void LoginExceptionThrownInvalidPassword() {
        // Given
        Auth auth = authData.createAuth();
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(authData.EMAIL);
        userEntity.setPassword(authData.PASSWORD);

        when(userRepository.findByEmail(auth.getEmail())).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches(auth.getPassword(), userEntity.getPassword())).thenReturn(false);

        // When/Then
        assertThrows(InvalidPasswordException.class, () -> authAdapter.login(auth));
    }
    @Test
    void LoginExceptionThrownUserNotFound() {
        // Given
        Auth auth = authData.createAuth();

        when(userRepository.findByEmail(auth.getEmail())).thenReturn(Optional.empty());

        // When/Then
        assertThrows(UsernameNotFoundException.class, () -> authAdapter.login(auth));
    }



    @Test
    void testRegisterAdmin() {
        User user = userData.createUser();
        String encodedPassword = userData.PASSWORD;
        String roleName = Constants.ROLE_ADMIN;
        RoleEntity roleEntity = roleData.roleAdminEntity();
        UserEntity userEntity = userData.createUserEntityAdmin();

        when(passwordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);
        when(roleRepository.findByName(roleName)).thenReturn(java.util.Optional.of(roleEntity));
        when(userRepository.save(any())).thenReturn(userEntity);
        when(jwtService.getToken(any())).thenReturn(authData.createToken());


        AuthResponse authResponse = authAdapter.registerAdmin(user);

        assertNotNull(authResponse);
        assertNotNull(authResponse.getToken());
    }

    @Test
    void testRegisterTutor() {
        User user = userData.createUser();
        String encodedPassword = userData.PASSWORD;
        String roleName = Constants.ROLE_TUTOR;
        RoleEntity roleEntity = roleData.roleTutorEntity();
        UserEntity userEntity = userData.createUserEntityTutor();

        when(passwordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);
        when(roleRepository.findByName(roleName)).thenReturn(java.util.Optional.of(roleEntity));
        when(userRepository.save(any())).thenReturn(userEntity);
        when(jwtService.getToken(any())).thenReturn(authData.createToken());


        AuthResponse authResponse = authAdapter.registerTutor(user);

        assertNotNull(authResponse);
        assertNotNull(authResponse.getToken());
    }
    @Test
    void testRegisterStudent() {
        User user = userData.createUser();
        String encodedPassword = userData.PASSWORD;
        String roleName = Constants.ROLE_STUDENT;
        RoleEntity roleEntity = roleData.roleTutorEntity();
        UserEntity userEntity = userData.createUserEntityStudent();

        when(passwordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);
        when(roleRepository.findByName(roleName)).thenReturn(java.util.Optional.of(roleEntity));
        when(userRepository.save(any())).thenReturn(userEntity);
        when(jwtService.getToken(any())).thenReturn("token");


        AuthResponse authResponse = authAdapter.registerStudent(user);

        assertNotNull(authResponse);
        assertNotNull(authResponse.getToken());
    }


}