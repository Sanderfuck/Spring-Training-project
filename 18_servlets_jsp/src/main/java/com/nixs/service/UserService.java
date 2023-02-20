package com.nixs.service;

import com.nixs.model.dto.UserDto;
import com.nixs.model.User;

import java.util.List;

public interface UserService {
    public boolean addUser(User user);

    public boolean updateUser(User user);

    public boolean deleteUser(Long id);

    public User getUser(Long id);

    public List<UserDto> getUsers();
}
