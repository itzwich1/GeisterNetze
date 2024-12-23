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
            // Neue Person erstellen
            Person person = new Person();
            person.setVorname("Max");
            person.setNachname("Mustermann");
            person.setTelefonnummer(123456789);
            person.setRolle(Person.Role.BERGER);

            // Neues Login erstellen
            Login login = new Login();
            login.setBenutzername("max.mustermann");
            login.setPasswort("sicheresPasswort123");
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

            System.out.println("Person und Login erfolgreich hinzugefügt.");
            System.out.println("Person-ID: " + person.getPersonID());
            System.out.println("Login-ID: " + login.getLoginID());
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
