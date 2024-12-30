package com.geisternetze.services;


import com.geisternetze.entities.Login;
import com.geisternetze.entities.Person;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

@ApplicationScoped
public class RegisterService {

    // EntityManagerFactory und EntityManager für die Datenbankoperationen
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPersistenceUnit");
    private EntityManager em = emf.createEntityManager();

    public void registerUser(String benutzername, String password, String email,
                             String vorname, String nachname, Person.Role role, int phone) {
        try {
            // Erstellen und initialisieren der Login-Entität
            Login login = new Login();
            login.setBenutzername(benutzername);
            login.setPasswort(password);
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

            // Transaktion starten und persistieren
            em.getTransaction().begin();
            em.persist(person);
            em.persist(login);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }


}
