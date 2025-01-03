package com.geisternetze.services;

import com.geisternetze.entities.Geisternetz;
import com.geisternetze.entities.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;


public class GeisternetzMeldungService {

    public void meldeGeisternetz(boolean anonym, String vorname, String nachname, Integer telefonnummer, Double breitengrad, Double laengengrad, Integer groesse) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        Person person = new Person();

        person.setVorname(vorname);
        person.setRolle(Person.Role.MELDER);

        if(!anonym){
            person.setNachname(nachname);
            person.setTelefonnummer(telefonnummer);
        }

        em.getTransaction().begin();

        em.persist(person);

        // Neues Geisternetz erstellen
        Geisternetz geisternetz = new Geisternetz();
        geisternetz.setBreitengrad(breitengrad);
        geisternetz.setLaengengrad(laengengrad);
        geisternetz.setGroesse(groesse);
        geisternetz.setStatus(Geisternetz.Status.GEMELDET);
        geisternetz.setErfassungsdatum(LocalDateTime.now());
        geisternetz.setMelder(person);

        // Geisternetz speichern
        em.persist(geisternetz);


        em.getTransaction().commit();


        // EntityManager schließen
        em.close();
        emf.close();


    }

}
