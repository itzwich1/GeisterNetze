package com.geisternetze.services;


import com.geisternetze.entities.Login;
import com.geisternetze.entities.Person;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDateTime;

@Stateless
public class RegisterService {

    @PersistenceContext
    private EntityManager em;

    public boolean registerUser(String benutzername, String password, String email,
                             String vorname, String nachname, Person.Role role, String phone) {
        try {

            Long emailCount = em.createQuery(
                            "SELECT COUNT(l) FROM Login l WHERE l.email = :email", Long.class)
                    .setParameter("email", email)
                    .getSingleResult();

            if (emailCount > 0) {
                // Fehlermeldung hinzufügen
                FacesContext.getCurrentInstance().addMessage("registrationForm:email",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Die E-Mail-Adresse ist bereits registriert."));
                return false;
            }

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            // Erstellen und initialisieren der Login-Entität
            Login login = new Login();
            login.setBenutzername(benutzername);
            login.setPasswort(hashedPassword);
            login.setEmail(email);
            login.setErstelltAm(LocalDateTime.now());

            // Erstellen und initialisieren der Person-Entität
            Person person = new Person();
            person.setTelefonnummer(phone);
            person.setVorname(vorname);
            person.setNachname(nachname);
            person.setRolle(role);

            // Verknüpfen der Login- und Person-Entitäten
            login.setPerson(person);

            em.persist(person);
            em.persist(login);


            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }


}
