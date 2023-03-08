package com.nixs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @RequestMapping(value = {"/login", "/"})
    public String getLogin() {
        return "login";
    }
}
