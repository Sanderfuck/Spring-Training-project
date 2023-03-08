package com.nixs.controller;

import com.nixs.model.dto.UserDto;
import com.nixs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GetUsersController {
    private final UserService userService;

    @GetMapping("/admin-home")
    public ModelAndView getLogin(ModelAndView modelAndView) {
        List<UserDto> userList = userService.getUsers();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("admin-home");
        return modelAndView;
    }
}
