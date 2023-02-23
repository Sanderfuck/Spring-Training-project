package com.nixs.service;

import com.nixs.model.User;

import java.util.ArrayList;
import java.util.List;

public class ValidationService {
    public static List<String> validateUser(User user, Long userId) {
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
            if (userId == null) {
                errors.add("Password is required");
            }
        }
        return errors;
    }
}
