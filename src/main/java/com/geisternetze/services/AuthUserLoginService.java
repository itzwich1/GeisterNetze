package com.geisternetze.services;



import com.geisternetze.beans.UserSessionBean;
import com.geisternetze.entities.Login;
import com.geisternetze.entities.Person;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.*;
import org.mindrot.jbcrypt.BCrypt;

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
                    "SELECT l FROM Login l WHERE l.benutzername = :username",
                    Login.class
            );
            loginQuery.setParameter("username", username);

            Login login = loginQuery.getSingleResult();

            if (login != null && BCrypt.checkpw(password, login.getPasswort())) {
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
