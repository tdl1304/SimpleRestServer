package com.simplerest.server.services;
import com.simplerest.server.dao.AdresseRepository;
import com.simplerest.server.models.Adresse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdresseService implements ServiceLayer<Adresse>{

    Logger logger = LoggerFactory.getLogger(AdresseService.class);
    @Autowired
    private AdresseRepository adresseRepository;

    public AdresseService() {}

    @Override
    public List<Adresse> getAll() {
        logger.info("Getting all adresses");
        List<Adresse> result = new ArrayList<>();
        adresseRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Adresse findById(int theId) {
        logger.info("Finding adress: " + theId);
        Optional<Adresse> found = adresseRepository.findById(theId);
        return found.orElse(null);
    }

    @Override
    public Adresse save(Adresse entity) {
        logger.info("saving entity to db: "+ entity);
        return adresseRepository.save(entity);
    }

    @Override
    public void deleteById(int theId) {
        logger.info("deleting adress id="+ theId);
        adresseRepository.deleteById(theId);
    }

    @Override
    public Adresse update(Adresse adresse) {
        logger.info("Updating adress");
        return adresseRepository.save(adresse);
    }
}
