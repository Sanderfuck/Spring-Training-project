package com.nixs.service;

import com.nixs.dao.HibernateDao;
import com.nixs.exception.DataProcessingException;
import com.nixs.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final HibernateDao<Role> dao;

    @Autowired
    public RoleServiceImpl(HibernateDao<Role> dao) {
        this.dao = dao;
    }

    public Role getRoleByName(String name) {
        return dao.findByName(name).orElseThrow(() -> new RuntimeException("Role not finded by name"));
    }

    public List<Role> getAllRoles() {
        return dao.findAll();
    }
}
