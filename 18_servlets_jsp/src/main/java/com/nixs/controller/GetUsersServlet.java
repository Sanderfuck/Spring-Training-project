package com.nixs.controller;

import com.nixs.model.dto.UserDto;
import com.nixs.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin-home")
public class GetUsersServlet extends HttpServlet {
    private UserServiceImpl userServiceImpl;

    @Override
    public void init() {
        userServiceImpl = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UserDto> userList = userServiceImpl.getUsers();

        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/WEB-INF/views/admin-home.jsp").forward(request, response);
    }
}
