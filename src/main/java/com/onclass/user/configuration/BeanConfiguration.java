package com.onclass.user.configuration;


import com.onclass.user.adapters.driven.jpa.mysql.adapter.UserAdapter;
import com.onclass.user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.onclass.user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.onclass.user.domain.api.IUserServicePort;
import com.onclass.user.domain.api.usecase.UserUseCase;
import com.onclass.user.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;

    private final IUserEntityMapper userEntityMapper;
    @Bean
    public IUserPersistencePort userPersistencePort() {

        return new UserAdapter(userRepository, userEntityMapper);

    }
    @Bean
    public IUserServicePort userServicePort() {

        return new UserUseCase(userPersistencePort());
    }
}
