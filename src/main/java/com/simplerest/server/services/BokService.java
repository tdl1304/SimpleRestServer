package com.simplerest.server.services;
import com.simplerest.server.dao.BokDao;
import com.simplerest.server.model.Bok;
import com.simplerest.server.model.Forfatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BokService implements ServiceLayer<Bok>{
    Logger logger = LoggerFactory.getLogger(BokService.class);
    @Autowired
    private BokDao bokDao;
    @Autowired
    private ForfatterService forfatterService;


    public BokService() {}

    @Override
    public List<Bok> getAll() {
        logger.info("Getting all books");
        return bokDao.loadAll();
    }

    @Override
    public Bok findById(int theId) {
        logger.info("Finding book: "+theId);
        return findById(theId);
    }

    /**
     * Find bok on title
     * @param title
     * @return Bok or null
     */
    public Bok findByTitle(String title) {
        logger.info("Finding book on title "+ title);
        Optional<Bok> found = getAll().stream()
                .filter(x->x.getTittel().equals(title))
                .findFirst();
        return found.orElse(null);
    }

    @Override
    public Bok save(Bok entity) {
        return bokDao.save(entity);
    }

    public Bok save(Bok entity, List<Integer> forfatterIds) {
        Set<Forfatter> forfatterSet = new HashSet<>();
        for (int id: forfatterIds) {
            Forfatter f = forfatterService.findById(id);
            if(f != null) {
                forfatterSet.add(f);
            }
        }
        entity.setForfatterSet(forfatterSet);
        return bokDao.save(entity);
    }

    @Override
    public Bok deleteById(int theId) {
        logger.info("deleting book: "+theId);
        return bokDao.delete(theId);
    }

    @Override
    public Bok update(Bok bok) {
        logger.info("updating book: "+bok);
        return bokDao.update(bok);
    }
}
