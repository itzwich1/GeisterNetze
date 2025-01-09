package com.geisternetze.TestData;


import com.geisternetze.entities.Login;
import com.geisternetze.entities.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

//Generiert bei einer leeren Datenbank testbenutzer
public class GenerateTestUsers {

    public GenerateTestUsers() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        try {

            // Pruefen, ob Eintraege in der Login-Tabelle vorhanden sind
            Long count = em.createQuery("SELECT COUNT(l) FROM Login l", Long.class).getSingleResult();

            if(count <= 0){

                // Neue Person erstellen
                Person person = new Person();
                person.setVorname("Max");
                person.setNachname("Mustermann");
                person.setTelefonnummer(123456789L);
                person.setRolle(Person.Role.BERGER);

                // Neues Login erstellen
                Login login = new Login();
                login.setBenutzername("max");
                login.setPasswort("1234");
                login.setEmail("max.mustermann@example.com");
                login.setErstelltAm(LocalDateTime.now());
                login.setPerson(person); // Verknüpfung mit der Person

                // Transaktion starten
                em.getTransaction().begin();

                // Zuerst die Person speichern (wegen der Beziehung)
                em.persist(person);

                // Dann das Login speichern
                em.persist(login);

                // Transaktion committen
                em.getTransaction().commit();

            }


        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // EntityManager schließen
            em.close();
            emf.close();
        }


    }

}
