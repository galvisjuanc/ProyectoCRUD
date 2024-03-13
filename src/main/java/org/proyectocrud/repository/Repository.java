package org.proyectocrud.repository;

import java.util.List;

public interface Repository <T> {
    List<T> findAll();

    T getById(Integer id);

    void save(T t);

    void delete(Integer id);
}
