package com.nixs.controller;

import com.nixs.model.Role;
import com.nixs.model.dto.UserDto;
import com.nixs.service.RoleService;
import com.nixs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RegistrationUserController {
    private final UserService userService;
    private final RoleService roleService;
    private List<Role> roles;

    @ModelAttribute("rolesList")
    public List<Role> getRoles() {
        if (roles == null) {
            roles = roleService.getAllRoles();
        }
        return roles;
    }

    @GetMapping("/registration")
    public String showRegisterForm(Model model) {
        model.addAttribute("rolesList", roles);
        return "registration";
    }

    @PostMapping("/registration")
    public String processRegisterForm(@ModelAttribute("userDto") @Valid UserDto userDto,
                                      BindingResult result,
                                      Model model) {
        if (result.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "registration";
        } else {
            userService.addUser(userDto);
            return "redirect:/login?registrationSuccess";
        }
    }
}
