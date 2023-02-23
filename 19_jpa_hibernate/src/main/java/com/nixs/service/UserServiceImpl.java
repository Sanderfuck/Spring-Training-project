package com.nixs.service;

import com.nixs.dao.HibernateDao;
import com.nixs.dao.HibernateUserDao;
import com.nixs.model.Role;
import com.nixs.model.User;
import com.nixs.model.dto.UserDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private HibernateDao<User> dao;
    private RoleService roleService;

    public UserServiceImpl() {
        dao = new HibernateUserDao();
        roleService = new RoleServiceImpl();
    }

    public boolean addUser(User user) {
        return dao.save(user);
    }

    public boolean updateUser(User user) {
        return dao.save(user);
    }

    public boolean deleteUser(Long id) {
        return dao.delete(id);
    }

    public User getUser(Long id) {
        return dao.findById(id).get();
    }

    public List<UserDto> getUsers() {
        Map<Long, String> roles = roleService.getAllRoles().stream()
                .collect(Collectors.toMap(Role::getId, Role::getName));

        return dao.findAll().stream()
                .map(user -> new UserDto(user, roles.get(user.getRoleId()))).collect(Collectors.toList());
    }
}
