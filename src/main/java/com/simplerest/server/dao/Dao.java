package com.simplerest.server.dao;

import java.util.List;

public interface Dao<T> {
    T save(T t);
    T load(int id);
    T delete(int id);
    T update(T t);
    List<T> loadAll();
}
