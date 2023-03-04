package com.nixs.service;

import com.nixs.model.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean addUser(UserDto user);

    boolean updateUser(UserDto user);

    void deleteUser(Long id);

    Optional<UserDto> getUserByName(String name);

    UserDto getUser(Long id);

    List<UserDto> getUsers();
}
