package com.geisternetze.services;



import com.geisternetze.entities.Login;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.sql.*;



@Stateless
public class AuthUserLogin {

public boolean authenticate(String username, String password){
    try{

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Login> query = em.createQuery("SELECT l FROM Login l WHERE l.benutzername = :username AND l.passwort = :password", Login.class);

        query.setParameter("username", username);
        query.setParameter("password", password);

        Login login = query.getSingleResult();

        em.close();
        emf.close();

        return login != null;

    }catch (Exception e){
        //e.printStackTrace();
        return false;
    }
}

    public AuthUserLogin()  {

    }




}
