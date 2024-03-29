package com.nixs.service;

import com.nixs.dao.HibernateDao;
import com.nixs.dao.HibernateUserDao;
import com.nixs.model.User;
import com.nixs.model.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private HibernateDao<User> dao;

    public UserServiceImpl() {
        dao = new HibernateUserDao();
    }

    public boolean addUser(User user) {
        return dao.save(user);
    }

    public boolean updateUser(User user) {
        return dao.save(user);
    }

    public void deleteUser(Long id) {
        dao.delete(id);
    }

    public User getUser(Long id) {
        return dao.findById(id).get();
    }

    public List<UserDto> getUsers() {
        return dao.findAll().stream()
                .map(UserDto::new).collect(Collectors.toList());
    }
}
