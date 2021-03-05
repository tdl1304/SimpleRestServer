package com.simplerest.server.services;
import com.simplerest.server.dao.ForfatterRepository;
import com.simplerest.server.models.Adresse;
import com.simplerest.server.models.Bok;
import com.simplerest.server.models.Forfatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ForfatterService implements ServiceLayer<Forfatter>{
    Logger logger = LoggerFactory.getLogger(ForfatterService.class);
    @Autowired
    private ForfatterRepository forfatterRepository;
    @Autowired
    private AdresseService adresseService;
    @Autowired
    private BokService bokService;


    public ForfatterService(){}

    @Override
    public List<Forfatter> getAll() {
        logger.info("Getting all forfattere");
        List<Forfatter> result = new ArrayList<>();
        forfatterRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Forfatter findById(int theId) {
        logger.info("Finding forfatter: "+theId);
        Optional<Forfatter> found = forfatterRepository.findById(theId);
        return found.orElse(null);
    }

    /**
     * Find forfatters by last name
     * @param lastname
     * @return List of forfatters
     */
    public List<Forfatter> findByLastname(String lastname) {
        return forfatterRepository.findAllByEtternavn(lastname);
    }

    @Override
    public Forfatter save(Forfatter entity) {
        return forfatterRepository.save(entity);
    }

    public Forfatter save(Forfatter entity, int adresseId, List<Integer> bokIds) {
        Adresse adresseFound = adresseService.findById(adresseId);
        List<Bok> bokList = new ArrayList<>();
        if(adresseFound != null) {
            entity.setAdresse(adresseFound);
        }
        if(bokIds != null && bokIds.size() != 0) {
            for (int id: bokIds) {
                Bok b = bokService.findById(id);
                if(b != null) {
                    bokList.add(b);
                }
            }
        }
        entity.setBokList(bokList);
        entity.setAdresse(adresseFound);
        return forfatterRepository.save(entity);
    }

    @Override
    public void deleteById(int theId) {
        logger.info("deleting forfatter: "+theId);
        forfatterRepository.deleteById(theId);
    }

    @Override
    public Forfatter update(Forfatter forfatter) {
        logger.info("updating forfatter: "+forfatter);
        return forfatterRepository.save(forfatter);

    }
}
