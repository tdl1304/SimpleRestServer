package com.simplerest.server.dao;

import com.simplerest.server.models.Bok;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BokRepository extends CrudRepository<Bok, Integer> {
    Bok save(Bok bok);
    Optional<Bok> findById(Integer id);
    Bok findByTittel(String tittel);
    Iterable<Bok> findAll();
    void deleteById(Integer id);
}
