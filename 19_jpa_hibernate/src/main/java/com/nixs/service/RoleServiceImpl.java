package com.nixs.service;

import com.nixs.dao.HibernateDao;
import com.nixs.dao.HibernateRoleDao;
import com.nixs.model.Role;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private HibernateDao<Role> dao;

    public RoleServiceImpl() {
        dao = new HibernateRoleDao();
    }

    public Role getRoleByName(String name) {
        return dao.findByName(name).get();
    }

    public List<Role> getAllRoles() {
        return dao.findAll();
    }

    public Role getRoleById(Long id) {
        return dao.findById(id).get();
    }
}
