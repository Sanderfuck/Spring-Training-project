package com.nixs.service;

import com.nixs.dao.Dao;
import com.nixs.dao.RoleDao;
import com.nixs.model.Role;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private Dao<Role> dao;

    public RoleServiceImpl() {
        this.dao = new RoleDao();
    }

    public Role getRoleByName(String name) {
        return dao.getByName(name);
    }

    public List<Role> getAllRoles() {
        return dao.getAll();
    }

    public Role getRoleById(Long id) {
        return dao.getById(id);
    }
}
