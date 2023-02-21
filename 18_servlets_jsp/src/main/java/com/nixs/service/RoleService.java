package com.nixs.service;

import com.nixs.model.Role;

import java.util.List;

public interface RoleService {

    Role getRoleByName(String name);

    List<Role> getAllRoles();

    Role getRoleById(Long id);
}
