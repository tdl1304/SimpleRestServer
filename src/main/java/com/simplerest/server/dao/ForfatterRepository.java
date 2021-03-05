package com.simplerest.server.dao;

import com.simplerest.server.models.Forfatter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ForfatterRepository extends CrudRepository<Forfatter, Integer> {
    Forfatter save(Forfatter forfatter);
    Optional<Forfatter> findById(Integer id);
    Iterable<Forfatter> findAll();
    void deleteById(Integer id);
    List<Forfatter> findAllByEtternavn(String lastname);
}
