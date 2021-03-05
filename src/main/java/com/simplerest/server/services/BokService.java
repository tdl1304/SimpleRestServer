package com.simplerest.server.services;
import com.simplerest.server.dao.BokRepository;
import com.simplerest.server.models.Bok;
import com.simplerest.server.models.Forfatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BokService implements ServiceLayer<Bok>{
    Logger logger = LoggerFactory.getLogger(BokService.class);
    @Autowired
    private BokRepository bokRepository;
    @Autowired
    private ForfatterService forfatterService;


    public BokService() {}

    @Override
    public List<Bok> getAll() {
        logger.info("Getting all books");
        List<Bok> result = new ArrayList<>();
        bokRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Bok findById(int theId) {
        logger.info("Finding book: "+theId);
        Optional<Bok> found = bokRepository.findById(theId);
        return found.orElse(null);
    }

    /**
     * Find bok on title
     * @param title
     * @return Bok or null
     */
    public Bok findByTitle(String title) {
        logger.info("Finding book on title "+ title);
        return bokRepository.findByTittel(title);
    }

    @Override
    public Bok save(Bok entity) {
        return bokRepository.save(entity);
    }

    public Bok save(Bok entity, List<Integer> forfatterIds) {
        List<Forfatter> forfatterList = new ArrayList<>();
        for (int id: forfatterIds) {
            Forfatter f = forfatterService.findById(id);
            if(f != null) {
                forfatterList.add(f);
            }
        }
        entity.setForfatterList(forfatterList);
        return bokRepository.save(entity);
    }

    @Override
    public void deleteById(int theId) {
        logger.info("deleting book: "+theId);
        bokRepository.deleteById(theId);
    }

    @Override
    public Bok update(Bok bok) {
        logger.info("updating book: "+bok);
        return bokRepository.save(bok);
    }
}
