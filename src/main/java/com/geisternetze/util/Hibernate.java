package com.geisternetze.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class Hibernate {
    private final EntityManagerFactory entityManagerFactory;

    static String PERSISTENCEUNITNAME = "MyPersistenceUnit" ;
    public Hibernate() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCEUNITNAME);
    }

    @Produces
    @ApplicationScoped
    public   EntityManagerFactory getEntityManagerFactory(){
        return entityManagerFactory;
    }

    @Produces
    @ApplicationScoped
    public EntityManager createEntityManager() {

        return entityManagerFactory.createEntityManager();

    }
}
