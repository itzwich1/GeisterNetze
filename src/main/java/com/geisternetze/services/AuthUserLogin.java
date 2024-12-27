package com.geisternetze.services;



import com.geisternetze.beans.UserSessionBean;
import com.geisternetze.entities.Login;
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
        try{

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPersistenceUnit");
            EntityManager em = emf.createEntityManager();
            TypedQuery<Login> query = em.createQuery("SELECT l FROM Login l WHERE l.benutzername = :username AND l.passwort = :password", Login.class);

            query.setParameter("username", username);
            query.setParameter("password", password);

            Login login = query.getSingleResult();

            userSession.setUserId(login.getPerson().getPersonID());
            userSession.setUsername(login.getBenutzername());

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
