package com.simplerest.server.dao;

import com.simplerest.server.model.Forfatter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ForfatterDao implements Dao<Forfatter>{
    @PersistenceContext
    EntityManagerFactory emf;

    @Override
    public Forfatter save(Forfatter forfatter) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(forfatter);
        em.getTransaction().commit();
        em.close();
        return forfatter;
    }

    @Override
    public Forfatter load(int id) {
        EntityManager em = emf.createEntityManager();
        Forfatter forfatter = em.find(Forfatter.class, id);
        em.close();
        return forfatter;
    }

    @Override
    public Forfatter delete(int id) {
        EntityManager em = emf.createEntityManager();
        Forfatter forfatter = em.find(Forfatter.class, id);

        em.getTransaction().begin();
        em.remove(forfatter);
        em.getTransaction().commit();
        em.close();
        return forfatter;
    }

    @Override
    public Forfatter update(Forfatter forfatter) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(forfatter);
        em.getTransaction().commit();
        em.close();
        return forfatter;
    }

    @Override
    public List<Forfatter> loadAll() {
        try {
            EntityManager em = emf.createEntityManager();
            List<Forfatter> forfatters = em.createQuery("SELECT a FROM Forfatter a")
                    .getResultList();
            em.close();
            return forfatters;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
