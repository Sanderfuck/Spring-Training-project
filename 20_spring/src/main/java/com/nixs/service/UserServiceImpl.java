package com.nixs.service;

import com.nixs.dao.HibernateDao;
import com.nixs.dao.HibernateUserDao;
import com.nixs.model.User;
import com.nixs.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final HibernateDao<User> dao;

    @Autowired
    public UserServiceImpl(HibernateDao<User> dao) {
        this.dao = dao;
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

    public Optional<User> getUserByName(String name) {
        return dao.findByName(name);
    }

    public List<UserDto> getUsers() {
        return dao.findAll().stream()
                .map(UserDto::new).collect(Collectors.toList());
    }
}
