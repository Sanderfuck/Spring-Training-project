package com.nixs;

import com.nixs.config.AppConfig;
import com.nixs.model.Role;
import com.nixs.model.dto.UserDto;
import com.nixs.service.RoleService;
import com.nixs.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class ApplicationStart {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        RoleService roleService = context.getBean(RoleService.class);

        List<UserDto> users = userService.getUsers();
        List<Role> roles = roleService.getAllRoles();

        System.out.println(users);
        System.out.println(roles);
    }
}
