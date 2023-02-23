package com.nixs.controller;

import com.nixs.service.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {
    private UserServiceImpl userServiceImpl;

    @Override
    public void init() {
        userServiceImpl = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        userServiceImpl.deleteUser(userId);
        resp.sendRedirect(req.getContextPath() + "/admin-home");
    }
}
