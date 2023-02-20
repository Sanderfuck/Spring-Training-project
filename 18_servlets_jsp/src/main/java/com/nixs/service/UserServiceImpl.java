package com.nixs.service;

import com.nixs.dao.Dao;
import com.nixs.dao.UserDao;
import com.nixs.model.Role;
import com.nixs.model.User;
import com.nixs.model.dto.UserDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private Dao<User> dao;
    private RoleService roleService;

    public UserServiceImpl() {
        dao = new UserDao();
        roleService = new RoleServiceImpl();
    }

    public boolean addUser(User user) {
        user.setPassword(AuthenticationServiceImpl.encryptPassword(user.getPassword()));
        return dao.add(user);
    }

    public boolean updateUser(User user) {
//        user.setPassword(AuthenticationServiceImpl.encryptPassword(user.getPassword()));
        return dao.update(user);
    }

    public boolean deleteUser(Long id) {
        return dao.delete(id);
    }

    public User getUser(Long id) {
        return dao.getById(id);
    }

    public List<UserDto> getUsers() {
        Map<Long, String> roles = roleService.getAllRoles().stream()
                .collect(Collectors.toMap(Role::getId, Role::getName));

        return dao.getAll().stream()
                .map(user -> new UserDto(user, roles.get(user.getRoleId()))).collect(Collectors.toList());
    }
}
