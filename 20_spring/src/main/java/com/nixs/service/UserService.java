package com.nixs.service;

import com.nixs.model.User;
import com.nixs.model.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean addUser(User user);

    boolean updateUser(User user);

    void deleteUser(Long id);

    Optional<User> getUserByName(String name);

    User getUser(Long id);

    List<UserDto> getUsers();

}
