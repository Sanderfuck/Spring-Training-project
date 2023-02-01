package com.nixs;

import com.nixs.jdbc.JdbcRoleDao;
import com.nixs.jdbc.JdbcUserDao;
import com.nixs.service.DbService;

public class ApplicationStart {

    public static void main(String[] args) {

        DbService dbService = new DbService();
        JdbcRoleDao roleDao = new JdbcRoleDao(dbService);
        System.out.println(roleDao.findAll());

        JdbcUserDao userDao = new JdbcUserDao(dbService);
        System.out.println(userDao.findAll());

    }
}
