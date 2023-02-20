package com.nixs.dao;

import java.util.List;

public interface Dao<T> {
    boolean add(T entity);

    boolean update(T entity);

    boolean delete(Long id);

    T getByName(String name);

    T getById(Long id);

    List<T> getAll();
}
