package com.nixs.service;

import com.nixs.dao.HibernateDao;
import com.nixs.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final HibernateDao<Role> dao;

    @Autowired
    public RoleServiceImpl(HibernateDao<Role> dao) {
        this.dao = dao;
    }

    public Role getRoleByName(String name) {
        Optional<Role> role = dao.findByName(name);
        if (role.isEmpty()) {
            throw new RuntimeException("Role not founded by name");
        }
        return role.get();
    }

    public List<Role> getAllRoles() {
        return dao.findAll();
    }
}
