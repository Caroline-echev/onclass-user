package com.onclass.user.adapters.driven.jpa.mysql.adapter;


import com.onclass.user.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.onclass.user.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.onclass.user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.onclass.user.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.onclass.user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.onclass.user.configuration.Constants;
import com.onclass.user.configuration.exception.AdminAlreadyExistsException;
import com.onclass.user.configuration.exception.NoDataFoundException;
import com.onclass.user.domain.model.Role;
import com.onclass.user.domain.model.User;
import com.onclass.user.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void registerUser(User user) {
        user.setRole( getDefaultRole());
        if(userRepository.findByRole( roleEntityMapper.toEntity(user.getRole())).isPresent()){
            throw new AdminAlreadyExistsException(Constants.ADMIN_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        }

        userRepository.save(userEntityMapper.toEntity(user));
    }


    private Role getDefaultRole() {
        RoleEntity role =  roleRepository.findByName(Constants.ROLE_ADMIN)
                .orElseThrow(() -> new NoDataFoundException(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE));
        return  roleEntityMapper.toModel(role);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userEntityMapper::toUserModel)
                .orElseThrow(() -> new NoDataFoundException(Constants.USER_NOT_FOUND_EMAIL_EXCEPTION_MESSAGE + email));

    }

    @Override
    public void encoderPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
