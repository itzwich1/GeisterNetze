package com.geisternetze.services;



import com.geisternetze.beans.UserSessionBean;
import com.geisternetze.entities.Login;
import com.geisternetze.entities.Person;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

@Stateless
public class AuthUserLogin {


    @Inject
    private UserSessionBean userSession;

    public boolean authenticate(String username, String password){
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPersistenceUnit");
            EntityManager em = emf.createEntityManager();

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
                userSession.setUserId(login.getPerson().getPersonID());
                userSession.setUsername(login.getBenutzername());

                // Rolle der Person aus der Datenbank abfragen
                Person person = em.find(Person.class, login.getPerson().getPersonID());
                if (person != null) {
                    userSession.setRolle(person.getRolle()); // Rolle in die Session speichern
                }
            }

            em.close();
            emf.close();

            return login != null;

        } catch (Exception e) {
            // Logge den Fehler (kann bei ungültigen Anmeldeinformationen auftreten)
            e.printStackTrace();
            return false;
        }
    }

    public AuthUserLogin()  {

    }




}
