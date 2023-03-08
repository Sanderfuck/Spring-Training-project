package com.nixs.api;

import com.nixs.model.Role;
import com.nixs.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleManagement {
    private final RoleService roleService;

    @GetMapping("/{name}")
    public Role getRoleByName(@PathVariable String name) {
        return roleService.getRoleByName(name);
    }

    @GetMapping
    public List<Role> getRoles() {
        return roleService.getAllRoles();
    }
}
