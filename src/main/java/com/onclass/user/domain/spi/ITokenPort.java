package com.onclass.user.domain.spi;

import com.onclass.user.domain.model.User;

public interface ITokenPort {

    String getToken(User user);
}
