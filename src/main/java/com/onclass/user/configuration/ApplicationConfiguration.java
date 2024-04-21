package com.onclass.user.configuration;

import com.onclass.user.adapters.driven.jpa.mysql.adapter.UserAdapter;
import com.onclass.user.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.onclass.user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.onclass.user.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.onclass.user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.onclass.user.configuration.jwt.JwtService;
import com.onclass.user.domain.api.IAuthServicePort;
import com.onclass.user.domain.api.IUserServicePort;
import com.onclass.user.domain.api.usecase.AuthUseCase;
import com.onclass.user.domain.api.usecase.UserUseCase;
import com.onclass.user.domain.exception.EmailAlreadyExistsException;
import com.onclass.user.domain.spi.ITokenPort;
import com.onclass.user.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleEntityMapper roleEntityMapper;
    private final IRoleRepository roleRepository;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserAdapter(userRepository, userEntityMapper, roleRepository, roleEntityMapper, passwordEncoder());
    }


    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }

    @Bean
    public ITokenPort tokenPort() {
        return new JwtService();
    }

    @Bean
    public IAuthServicePort authServicePort() {
        return new AuthUseCase(userServicePort(), tokenPort());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public  PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailService() {
        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailAlreadyExistsException(Constants.USER_EMAIL_ALREADY_EXISTS_EXCEPTION_MESSAGE));
    }

}