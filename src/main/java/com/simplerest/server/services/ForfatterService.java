package com.simplerest.server.services;
import com.simplerest.server.dao.ForfatterDao;
import com.simplerest.server.model.Adresse;
import com.simplerest.server.model.Bok;
import com.simplerest.server.model.Forfatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ForfatterService implements ServiceLayer<Forfatter>{
    Logger logger = LoggerFactory.getLogger(ForfatterService.class);
    @Autowired
    private ForfatterDao forfatterDao;
    @Autowired
    private AdresseService adresseService;
    @Autowired
    private BokService bokService;


    public ForfatterService(){}

    @Override
    public List<Forfatter> getAll() {
        logger.info("Getting all forfattere");
        return forfatterDao.loadAll();
    }

    @Override
    public Forfatter findById(int theId) {
        logger.info("Finding forfatter: "+theId);
        return forfatterDao.load(theId);
    }

    /**
     * Find forfatters by last name
     * @param lastname
     * @return List of forfatters
     */
    public List<Forfatter> findByLastname(String lastname) {
        return forfatterDao.loadAll().stream()
                .filter(x->x.getEtternavn().equals(lastname))
                .collect(Collectors.toList());
    }

    @Override
    public Forfatter save(Forfatter entity) {
        return forfatterDao.save(entity);
    }

    public Forfatter save(Forfatter entity, int adresseId, List<Integer> bokIds) {
        Adresse adresseFound = adresseService.findById(adresseId);
        Set<Bok> bokSet = new HashSet<>();
        if(adresseFound != null) {
            entity.setAdresse(adresseFound);
        }
        if(bokIds != null && bokIds.size() != 0) {
            for (int id: bokIds) {
                Bok b = bokService.findById(id);
                if(b != null) {
                    bokSet.add(b);
                }
            }
        }
        entity.setBokSet(bokSet);
        entity.setAdresse(adresseFound);
        return forfatterDao.save(entity);
    }

    @Override
    public Forfatter deleteById(int theId) {
        logger.info("deleting forfatter: "+theId);
        return forfatterDao.delete(theId);
    }

    @Override
    public Forfatter update(Forfatter forfatter) {
        logger.info("updating forfatter: "+forfatter);
        return forfatterDao.update(forfatter);

    }
}
