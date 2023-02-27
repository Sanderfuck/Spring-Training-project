package com.nixs.controller;

import com.nixs.exception.AuthenticationException;
import com.nixs.model.User;
import com.nixs.service.AuthenticationService;
import com.nixs.service.AuthenticationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = {"/login", "/"})
public class LoginServlet extends HttpServlet {
    private static final Long ADMIN_ROLE_ID = 1L;
    private AuthenticationService authenticationService;

    @Override
    public void init() {
        authenticationService = new AuthenticationServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            User user = authenticationService.login(login, password);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if (Objects.equals(user.getRole().getId(), ADMIN_ROLE_ID)) {
                response.sendRedirect("/admin-home");
            } else {
                response.sendRedirect("/user-home");
            }
        } catch (AuthenticationException e) {
            request.setAttribute("errorMsg", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}
