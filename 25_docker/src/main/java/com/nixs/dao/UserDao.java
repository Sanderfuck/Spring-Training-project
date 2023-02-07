package com.nixs.dao;

import com.nixs.model.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    List<User> findAll();
    User findByLogin(String login);
    User findByEmail(String email);
}
