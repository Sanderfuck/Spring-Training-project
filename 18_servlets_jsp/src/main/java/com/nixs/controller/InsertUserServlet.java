package com.nixs.controller;

import com.nixs.model.Role;
import com.nixs.model.User;
import com.nixs.service.AuthenticationServiceImpl;
import com.nixs.service.RoleServiceImpl;
import com.nixs.service.UserServiceImpl;
import com.nixs.service.ValidationService;
import org.owasp.encoder.Encode;

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
public class InsertUserServlet extends HttpServlet {
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

        initUserId(request);
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
        User user = userConstructor(request);

        List<String> errors = ValidationService.validateUser(user, userId);
        if (errors.isEmpty()) {
            insertUser(user);
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

    private User userConstructor(HttpServletRequest request) {
        User user = new User();
        user.setId(userId);
        user.setEmail(Encode.forHtml(request.getParameter("email")));
        user.setFirstName(Encode.forHtml(request.getParameter("first_name")));
        user.setLastName(Encode.forHtml(request.getParameter("last_name")));
        user.setRoleId(Long.parseLong(request.getParameter("role")));
        String birthday = request.getParameter("birthday");
        setUserBirthday(user, birthday);

        String getParameterPassword = request.getParameter("password");
        String login;
        String password;

        if (userId != null) {
            login = userServiceImpl.getUser(userId).getLogin();
            password = getPasswordForEditUser(getParameterPassword);
        } else {
            login = Encode.forHtml(request.getParameter("login"));
            password = AuthenticationServiceImpl.encryptPassword(getParameterPassword);
        }

        user.setLogin(login);
        user.setPassword(password);
        return user;
    }

    private void initUserId(HttpServletRequest request) {
        String getParamUserId = request.getParameter("userId");

        if (getParamUserId != null) {
            userId = Long.parseLong(getParamUserId);
        }
    }

    private void setUserBirthday(User user, String birthday) {
        if (birthday.equals("")) {
            user.setBirthday(null);
        } else {
            user.setBirthday(Date.valueOf(birthday));
        }
    }

    private String getPasswordForEditUser(String getParameterPassword) {
        String password;
        if (getParameterPassword.isEmpty()) {
            password = userServiceImpl.getUser(userId).getPassword();
        } else {
            password = AuthenticationServiceImpl.encryptPassword(getParameterPassword);
        }
        return password;
    }

    private void insertUser(User user) {
        if (user.getId() == null) {
            userServiceImpl.addUser(user);
        } else {
            userServiceImpl.updateUser(user);
        }
    }

    private void clearUserId() {
        userId = null;
    }
}
