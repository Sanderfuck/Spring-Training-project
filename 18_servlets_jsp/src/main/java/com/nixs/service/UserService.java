package com.nixs.service;

import com.nixs.model.dto.UserDto;
import com.nixs.model.User;

import java.util.List;

public interface UserService {
    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(Long id);

    User getUser(Long id);

    List<UserDto> getUsers();
}
