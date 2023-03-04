package com.nixs.controller;

import com.nixs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DeleteUserController {
    private final UserService userService;

    @PostMapping("/delete")
    public String delete(@RequestParam("userId") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin-home";
    }
}
