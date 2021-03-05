package com.simplerest.server.services;
import com.simplerest.server.dao.AdresseDao;
import com.simplerest.server.model.Adresse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdresseService implements ServiceLayer<Adresse>{

    Logger logger = LoggerFactory.getLogger(AdresseService.class);
    @Autowired
    private AdresseDao adresseDao;

    public AdresseService() {}

    @Override
    public List<Adresse> getAll() {
        logger.info("Getting all adresses");
        return adresseDao.loadAll();
    }

    @Override
    public Adresse findById(int theId) {
        logger.info("Finding adress: " + theId);
        return adresseDao.load(theId);
    }

    @Override
    public Adresse save(Adresse entity) {
        logger.info("saving entity to db: "+ entity);
        return adresseDao.save(entity);
    }

    @Override
    public Adresse deleteById(int theId) {
        logger.info("deleting adress id="+ theId);
        return adresseDao.delete(theId);
    }

    @Override
    public Adresse update(Adresse adresse) {
        logger.info("Updating adress");
        return adresseDao.update(adresse);
    }
}
