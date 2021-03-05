package com.simplerest.server.dao;

import com.simplerest.server.models.Adresse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdresseRepository extends CrudRepository<Adresse, Integer> {
    Adresse save(Adresse adresse);
    Optional<Adresse> findById(Integer id);
    Iterable<Adresse> findAll();
    void deleteById(Integer id);
}
