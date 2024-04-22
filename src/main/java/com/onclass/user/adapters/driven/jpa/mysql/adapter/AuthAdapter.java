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
import com.onclass.user.domain.exception.NoDataFoundException;
import com.onclass.user.domain.model.Auth;
import com.onclass.user.domain.model.Role;
import com.onclass.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthAdapter {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleEntityMapper roleEntityMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

   public AuthResponse login(Auth auth) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword()));
            UserEntity userEntity = userRepository.findByEmail(auth.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException(Constants.USER_NOT_FOUND_EMAIL_EXCEPTION_MESSAGE + auth.getEmail()));
            String token = jwtService.getToken(userEntityMapper.toUserModel(userEntity));
            return new AuthResponse(token);
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }

    }
    public AuthResponse registerAdmin(User user ) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(getRole(Constants.ROLE_ADMIN));
        UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(user));

        return AuthResponse.builder()
                .token(jwtService.getToken(userEntityMapper.toUserModel(userEntity)))
                .build();

    }
    public AuthResponse registerTutor(User user) {

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(getRole(Constants.ROLE_TUTOR));
            UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(user));

            return AuthResponse.builder()
                    .token(jwtService.getToken(userEntityMapper.toUserModel(userEntity)))
                    .build();

    }
    private Role getRole(String roleName) {
        RoleEntity role =  roleRepository.findByName(roleName)
                .orElseThrow(() -> new NoDataFoundException(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE));
        return  roleEntityMapper.toModel(role);
    }



}