package com.nixs.service;

import com.nixs.exception.AuthenticationException;
import com.nixs.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;
}
