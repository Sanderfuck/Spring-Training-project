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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class InsertUserController {
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

    @GetMapping("/add-user")
    public ModelAndView showAddUserPage(@RequestParam(name = "userId", required = false) Long userId,
                                        ModelAndView model) {
        UserDto userDto;
        if (userId != null) {
            userDto = userService.getUser(userId);
        } else {
            userDto = new UserDto();
        }
        model.addObject("userDto", userDto);
        model.setViewName("add-user");
        return model;
    }

    @PostMapping("/add-user")
    public String insert( @ModelAttribute("userDto") @Valid UserDto userDto,
                         BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "add-user";
        } else {
            insertUser(userDto);
            return "redirect:/admin-home";
        }
    }

    private void insertUser(UserDto userDto) {
        if (userDto.getId() == null) {
            userService.addUser(userDto);
        } else {
            userService.updateUser(userDto);
        }
    }
}
