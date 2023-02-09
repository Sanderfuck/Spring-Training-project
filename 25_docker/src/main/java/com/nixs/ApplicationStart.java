package com.nixs;

import com.nixs.jdbc.GenericJdbcDao;
import com.nixs.jdbc.JdbcRoleDao;
import com.nixs.jdbc.JdbcUserDao;
import com.nixs.service.DbService;

public class ApplicationStart {
    public static void main(String[] args) {
        DbService dbService = new DbService();
        GenericJdbcDao roleDao = new JdbcRoleDao(dbService);
        System.out.println(roleDao.findAll());

        GenericJdbcDao userDao = new JdbcUserDao(dbService);
        System.out.println(userDao.findAll());
    }
}
