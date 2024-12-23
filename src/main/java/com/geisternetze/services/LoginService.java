package com.geisternetze.services;

import com.geisternetze.entities.Login;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class LoginService {

    @PersistenceContext(unitName = "MyPersistenceUnit")
    private EntityManager entityManager;

    public void addLogin(Login login) {
        entityManager.persist(login);
    }

}
