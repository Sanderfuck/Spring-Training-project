package com.nixs.service;

import com.nixs.dao.Dao;
import com.nixs.dao.UserDao;
import com.nixs.exception.AuthenticationException;
import com.nixs.model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class AuthenticationServiceImpl implements AuthenticationService {
    private Dao<User> dao;

    public AuthenticationServiceImpl() {
        dao = new UserDao();
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User user = dao.getByName(login);
        password = AuthenticationServiceImpl.encryptPassword(password);
        if (user == null) {
            throw new AuthenticationException("User or password is incorrect");
        }

        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new AuthenticationException("User or password is incorrect");
    }

    public static String encryptPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hash) {
                stringBuilder.append(String.format("%02x", b));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean patternMatches(String data, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(data)
                .matches();
    }
}
