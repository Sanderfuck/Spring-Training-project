package com.nixs.controller;

import com.nixs.model.Role;
import com.nixs.model.User;
import com.nixs.service.RoleService;
import com.nixs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        User user;
        String namePage;
        String nameButton;
        if (userId != null) {
            user = userService.getUser(userId);
            namePage = "Edit User";
            nameButton = "Edit";
        } else {
            user = new User();
            namePage = "Add User";
            nameButton = "Add";
        }
        model.addObject("nameButton", nameButton);
        model.addObject("namePage", namePage);
        model.addObject("userDto", user);
        model.setViewName("add-user");
        return model;
    }

    @PostMapping("/add-user")
    public String insert(@ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        } else {
            insertUser(user);
            return "redirect:/admin-home";
        }
    }

    private void insertUser(User user) {
        if (user.getId() == null) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
    }
}
