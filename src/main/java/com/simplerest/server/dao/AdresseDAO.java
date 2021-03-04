package com.simplerest.server.dao;

import com.simplerest.server.entities.Adresse;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class AdresseDAO implements Dao<Adresse>{

    @PersistenceUnit
    EntityManagerFactory emf;

    @Override
    public void save(Adresse adresse) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(adresse);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Adresse load(int id) {
        EntityManager em = emf.createEntityManager();
        Adresse adresse = em.find(Adresse.class, id);
        em.close();
        return adresse;
    }

    @Override
    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        Adresse adresse = em.find(Adresse.class, id);

        em.getTransaction().begin();
        em.remove(adresse);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Adresse adresse) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(adresse);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Adresse> loadAll() {
        EntityManager em = emf.createEntityManager();
        List<Adresse> adresses = em.createQuery("SELECT a FROM Adresse a")
                .getResultList();
        em.close();
        return adresses;
    }
}
