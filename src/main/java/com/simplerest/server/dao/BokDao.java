package com.simplerest.server.dao;

import com.simplerest.server.model.Bok;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BokDao implements Dao<Bok>{
    @PersistenceContext
    EntityManagerFactory emf;

    @Override
    public Bok save(Bok bok) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(bok);
        em.getTransaction().commit();
        em.close();
        return bok;
    }

    @Override
    public Bok load(int id) {
        EntityManager em = emf.createEntityManager();
        Bok bok = em.find(Bok.class, id);
        em.close();
        return bok;
    }

    @Override
    public Bok delete(int id) {
        EntityManager em = emf.createEntityManager();
        Bok bok = em.find(Bok.class, id);

        em.getTransaction().begin();
        em.remove(bok);
        em.getTransaction().commit();
        em.close();
        return bok;
    }

    @Override
    public Bok update(Bok bok) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(bok);
        em.getTransaction().commit();
        em.close();
        return bok;
    }

    @Override
    public List<Bok> loadAll() {
        try {
            EntityManager em = emf.createEntityManager();
            List<Bok> boker = em.createQuery("SELECT a FROM Bok a")
                    .getResultList();
            em.close();
            return boker;
        } catch(NullPointerException e) {
            return null;
        }

    }
}
