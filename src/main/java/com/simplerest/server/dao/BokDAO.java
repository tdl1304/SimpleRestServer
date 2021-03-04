package com.simplerest.server.dao;

import com.simplerest.server.entities.Bok;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BokDAO implements Dao<Bok>{
    @Override
    public void save(Bok bok) {

    }

    @Override
    public Bok load(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Bok bok) {

    }

    @Override
    public List<Bok> loadAll() {
        return null;
    }
}
