package com.onclass.user.domain.api;

import com.onclass.user.domain.model.Auth;
import com.onclass.user.domain.model.User;

public interface IAuthServicePort {

  String login(Auth auth);

  String registerAdmin(User user);
}
