package com.onclass.user.domain.api;

import com.onclass.user.domain.model.Auth;
import com.onclass.user.domain.model.Token;

public interface IAuthServicePort {

  String login(Auth auth);


}
