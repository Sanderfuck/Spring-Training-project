package com.nixs.jdbc;

import com.nixs.dao.Dao;
import com.nixs.service.DbService;

public abstract class GenericJdbcDao<E> implements Dao<E> {
    protected DbService dbService;

    public GenericJdbcDao(DbService dbService) {
        this.dbService = dbService;
    }

}
