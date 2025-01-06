package com.geisternetze.services;



import com.geisternetze.beans.UserSessionBean;
import com.geisternetze.entities.Login;
import com.geisternetze.entities.Person;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.*;

@Stateless
public class AuthUserLoginService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserSessionBean userSession;

    public boolean authenticate(String username, String password){
        try {

            // Login-Abfrage
            TypedQuery<Login> loginQuery = em.createQuery(
                    "SELECT l FROM Login l WHERE l.benutzername = :username AND l.passwort = :password",
                    Login.class
            );
            loginQuery.setParameter("username", username);
            loginQuery.setParameter("password", password);

            Login login = loginQuery.getSingleResult();

            if (login != null) {
                // Benutzer-ID und Benutzername in die Session speichern
                userSession.setPerson(login.getPerson());
                userSession.setBenutzername(login.getBenutzername());

                return true;
            }

            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public AuthUserLoginService()  {

    }
}
