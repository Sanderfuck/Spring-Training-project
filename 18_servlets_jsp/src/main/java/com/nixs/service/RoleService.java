package com.nixs.service;

import com.nixs.model.Role;

import java.util.List;

public interface RoleService {

    public Role getRoleByName(String name);

    public List<Role> getAllRoles();

    public Role getRoleById(Long id);
}
