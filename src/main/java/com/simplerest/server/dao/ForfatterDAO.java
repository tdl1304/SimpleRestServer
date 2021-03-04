package com.simplerest.server.dao;

import com.simplerest.server.entities.Forfatter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
public class ForfatterDAO implements Dao<Forfatter>{
    @PersistenceUnit
    EntityManagerFactory emf;

    @Override
    public void save(Forfatter forfatter) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(forfatter);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Forfatter load(int id) {
        EntityManager em = emf.createEntityManager();
        Forfatter forfatter = em.find(Forfatter.class, id);
        em.close();
        return forfatter;
    }

    @Override
    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        Forfatter forfatter = em.find(Forfatter.class, id);

        em.getTransaction().begin();
        em.remove(forfatter);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Forfatter forfatter) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(forfatter);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Forfatter> loadAll() {
        EntityManager em = emf.createEntityManager();
        List<Forfatter> forfatters = em.createQuery("SELECT a FROM Forfatter a")
                .getResultList();
        em.close();
        return forfatters;
    }
}
