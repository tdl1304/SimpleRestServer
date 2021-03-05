package com.simplerest.server.services;

import java.util.List;

public interface ServiceLayer<T> {
    List<T> getAll();
    T findById(int theId);
    T save(T entity);
    void deleteById(int theId);
    T update(T t);
}
