package com.nixs.controller;

import com.nixs.model.Role;
import com.nixs.model.User;
import com.nixs.service.RoleServiceImpl;
import com.nixs.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/add-user")
public class AddUpdateUserServlet extends HttpServlet {
    private UserServiceImpl userServiceImpl;
    private Long userId;
    private List<Role> roles;
    private String namePage;
    private String nameButton;

    @Override
    public void init() {
        userServiceImpl = new UserServiceImpl();
        roles = new RoleServiceImpl().getAllRoles();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String getParamUserId = request.getParameter("userId");

        if (getParamUserId != null) {
            userId = Long.parseLong(getParamUserId);
        }

        User user = null;

        if (userId != null) {
            user = userServiceImpl.getUser(userId);
            namePage = "Edit User";
            nameButton = "Edit";
        } else {
            namePage = "Add User";
            nameButton = "Add";
        }

        request.setAttribute("nameButton", nameButton);
        request.setAttribute("namePage", namePage);
        request.setAttribute("rolesList", roles);
        request.setAttribute("userDto", user);
        request.getRequestDispatcher("/WEB-INF/views/add-user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("rolesList", roles);

        User user = new User();
        user.setId(userId);
        user.setEmail(request.getParameter("email"));
        user.setFirstName(request.getParameter("first_name"));
        user.setLastName(request.getParameter("last_name"));
        user.setRoleId(Long.parseLong(request.getParameter("role")));
        String birthday = request.getParameter("birthday");
        if (birthday.equals("")) {
            user.setBirthday(null);
        } else {
            user.setBirthday(Date.valueOf(birthday));
        }

        if (userId != null) {
            user.setLogin(userServiceImpl.getUser(userId).getLogin());
            user.setPassword(userServiceImpl.getUser(userId).getPassword());
        } else {
            user.setLogin(request.getParameter("login"));
            user.setPassword(request.getParameter("password"));
        }

        List<String> errors = validateUser(user);
        if (errors.isEmpty()) {
            if (user.getId() == null) {
                userServiceImpl.addUser(user);
            } else {
                userServiceImpl.updateUser(user);
            }
            response.sendRedirect("admin-home");
        } else {
            request.setAttribute("nameButton", nameButton);
            request.setAttribute("namePage", namePage);
            request.setAttribute("userDto", user);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/WEB-INF/views/add-user.jsp").forward(request, response);
        }
        clearUserId();
    }

    private List<String> validateUser(User user) {
        List<String> errors = new ArrayList<>();

        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            errors.add("Login is required");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            errors.add("Email is required");
        }

        if (user.getRoleId() == null || user.getRoleId().toString().isEmpty()) {
            errors.add("Role is required");
        }

        if (user.getBirthday() == null || user.getBirthday().toString().isEmpty()) {
            errors.add("Date is required");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            errors.add("Password is required");
        }
        return errors;
    }

    private void clearUserId() {
        userId = null;
    }
}
