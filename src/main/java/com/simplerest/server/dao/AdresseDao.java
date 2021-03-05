package com.simplerest.server.dao;

import com.simplerest.server.model.Adresse;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class AdresseDao implements Dao<Adresse>{

    @PersistenceContext
    EntityManagerFactory emf;

    @Override
    public Adresse save(Adresse adresse) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(adresse);
        em.getTransaction().commit();
        em.close();
        return adresse;
    }

    @Override
    public Adresse load(int id) {
        EntityManager em = emf.createEntityManager();
        Adresse adresse = em.find(Adresse.class, id);
        em.close();
        return adresse;
    }

    @Override
    public Adresse delete(int id) {
        EntityManager em = emf.createEntityManager();
        Adresse adresse = em.find(Adresse.class, id);

        em.getTransaction().begin();
        em.remove(adresse);
        em.getTransaction().commit();
        em.close();
        return adresse;
    }

    @Override
    public Adresse update(Adresse adresse) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(adresse);
        em.getTransaction().commit();
        em.close();
        return adresse;
    }

    @Override
    public List<Adresse> loadAll() {
        try {
            EntityManager em = emf.createEntityManager();
            List<Adresse> adresses = em.createQuery("SELECT a FROM Adresse a")
                    .getResultList();
            em.close();
            return adresses;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
