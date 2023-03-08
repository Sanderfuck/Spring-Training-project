package com.nixs.dao;

import java.util.List;
import java.util.Optional;

public interface HibernateDao<T> {
    boolean save(T t);

    void delete(Long id);

    Optional<T> findById(Long id);

    Optional<T> findByName(String name);

    List<T> findAll();
}
