package com.simplerest.server.dao;

import com.simplerest.server.entities.Forfatter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ForfatterDAO implements Dao<Forfatter>{
    @Override
    public void save(Forfatter forfatter) {

    }

    @Override
    public Forfatter load(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Forfatter forfatter) {

    }

    @Override
    public List<Forfatter> loadAll() {
        return null;
    }
}
