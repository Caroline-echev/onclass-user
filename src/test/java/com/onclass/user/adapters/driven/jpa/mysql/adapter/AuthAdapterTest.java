package com.onclass.user.adapters.driven.jpa.mysql.adapter;

import com.onclass.user.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.onclass.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.onclass.user.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.onclass.user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.onclass.user.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.onclass.user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.onclass.user.adapters.driving.http.dto.response.auth.AuthResponse;
import com.onclass.user.configuration.Constants;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    void testLogin_Successful() {
        Auth auth = new Auth("test@example.com", "password");
        UserEntity userEntity = userData.createUserEntity();
        String token =  authData.createToken();

        when(authenticationManager.authenticate(any())).thenReturn(mock(Authentication.class));
        when(userRepository.findByEmail(auth.getEmail())).thenReturn(java.util.Optional.of(userEntity));
        when(jwtService.getToken(any())).thenReturn(token);

        AuthAdapter authAdapter = new AuthAdapter(userRepository, roleRepository, userEntityMapper, roleEntityMapper, jwtService, passwordEncoder, authenticationManager);

        AuthResponse authResponse = authAdapter.login(auth);

        assertNotNull(authResponse);
        assertEquals(token, authResponse.getToken());
    }


    @Test
    public void testRegisterAdmin() {
        User user = userData.createUserAdmin();
        String encodedPassword = "encodedPassword";
        String roleName = Constants.ROLE_ADMIN;
        RoleEntity roleEntity = roleData.roleAdminEntity();
        UserEntity userEntity = userData.createUserEntity();

        when(passwordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);
        when(roleRepository.findByName(roleName)).thenReturn(java.util.Optional.of(roleEntity));
        when(userRepository.save(any())).thenReturn(userEntity);
        when(jwtService.getToken(any())).thenReturn("token");

        AuthAdapter authAdapter = new AuthAdapter(userRepository, roleRepository, userEntityMapper, roleEntityMapper, jwtService, passwordEncoder, authenticationManager);

        AuthResponse authResponse = authAdapter.registerAdmin(user);

        assertNotNull(authResponse);
        assertNotNull(authResponse.getToken());
    }

}