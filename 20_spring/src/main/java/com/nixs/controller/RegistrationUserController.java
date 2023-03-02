package com.nixs.controller;

import com.nixs.model.User;
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

@Controller
@RequiredArgsConstructor
public class RegistrationUserController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/registration")
    public String showRegisterForm(Model model) {
        model.addAttribute("rolesList", roleService.getAllRoles());
        return "registration";
    }

    @PostMapping("/registration")
    public String processRegisterForm(@Valid @ModelAttribute("user") User user,
                                      BindingResult result,
                                      Model model) {

        if (result.hasErrors()) {
            model.addAttribute("rolesList", roleService.getAllRoles());
            return "registration";
        }

        try {
            userService.addUser(user);
            return "redirect:/login?registerSuccess";
        } catch (Exception e) {
            result.rejectValue("email", "error.userDto", e.getMessage());
            model.addAttribute("rolesList", roleService.getAllRoles());
            return "registration";
        }
    }
}
