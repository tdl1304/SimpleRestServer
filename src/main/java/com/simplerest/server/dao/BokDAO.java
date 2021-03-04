package com.simplerest.server.dao;

import com.simplerest.server.entities.Adresse;
import com.simplerest.server.entities.Bok;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
public class BokDAO implements Dao<Bok>{
    @PersistenceUnit
    EntityManagerFactory emf;

    @Override
    public void save(Bok bok) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(bok);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Bok load(int id) {
        EntityManager em = emf.createEntityManager();
        Bok bok = em.find(Bok.class, id);
        em.close();
        return bok;
    }

    @Override
    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        Bok bok = em.find(Bok.class, id);

        em.getTransaction().begin();
        em.remove(bok);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Bok bok) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(bok);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Bok> loadAll() {
        EntityManager em = emf.createEntityManager();
        List<Bok> boker = em.createQuery("SELECT a FROM Bok a")
                .getResultList();
        em.close();
        return boker;
    }
}
