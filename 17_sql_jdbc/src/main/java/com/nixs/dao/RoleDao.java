package com.nixs.dao;

import com.nixs.model.Role;

public interface RoleDao extends Dao<Role>{
    Role findByName(String name);
}
