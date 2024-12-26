package com.geisternetze.TestData;

import com.geisternetze.entities.Geisternetz;
import com.geisternetze.entities.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class GenerateTestGeisternetze {

    public GenerateTestGeisternetze() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        try {
            // Prüfen, ob Einträge in der Geisternetz-Tabelle vorhanden sind
            Long count = em.createQuery("SELECT COUNT(g) FROM Geisternetz g", Long.class).getSingleResult();

            if (count <= 0) {
                // Beispielperson 1 erstellen
                Person melder1 = new Person();
                melder1.setVorname("Anna");
                melder1.setNachname("Schmidt");
                melder1.setTelefonnummer(987654321);
                melder1.setRolle(Person.Role.MELDER);

                // Beispielperson 2 erstellen
                Person melder2 = new Person();
                melder2.setVorname("Peter");
                melder2.setNachname("Meier");
                melder2.setTelefonnummer(567891234);
                melder2.setRolle(Person.Role.MELDER);

                // Beispielperson 3 erstellen
                Person melder3 = new Person();
                melder3.setVorname("Lisa");
                melder3.setNachname("Klein");
                melder3.setTelefonnummer(234567890);
                melder3.setRolle(Person.Role.MELDER);

                // Beispiel-Geisternetz 1 erstellen
                Geisternetz geisternetz1 = new Geisternetz();
                geisternetz1.setLaengengrad(12.3456);
                geisternetz1.setBreitengrad(78.9101);
                geisternetz1.setGroesse(20);
                geisternetz1.setStatus(Geisternetz.Status.GEMELDET);
                geisternetz1.setErfassungsdatum(LocalDateTime.now());
                geisternetz1.setMelder(melder1);

                // Beispiel-Geisternetz 2 erstellen
                Geisternetz geisternetz2 = new Geisternetz();
                geisternetz2.setLaengengrad(13.4567);
                geisternetz2.setBreitengrad(79.0112);
                geisternetz2.setGroesse(15);
                geisternetz2.setStatus(Geisternetz.Status.BERGUNG_BEVORSTEHEND);
                geisternetz2.setErfassungsdatum(LocalDateTime.now());
                geisternetz2.setMelder(melder2);

                // Beispiel-Geisternetz 3 erstellen
                Geisternetz geisternetz3 = new Geisternetz();
                geisternetz3.setLaengengrad(14.5678);
                geisternetz3.setBreitengrad(80.1123);
                geisternetz3.setGroesse(10);
                geisternetz3.setStatus(Geisternetz.Status.GEBORGEN);
                geisternetz3.setErfassungsdatum(LocalDateTime.now());
                geisternetz3.setMelder(melder3);

                // Beispiel-Geisternetz 4 erstellen
                Geisternetz geisternetz4 = new Geisternetz();
                geisternetz4.setLaengengrad(14.5678);
                geisternetz4.setBreitengrad(80.1123);
                geisternetz4.setGroesse(10);
                geisternetz4.setStatus(Geisternetz.Status.GEMELDET);
                geisternetz4.setErfassungsdatum(LocalDateTime.now());
                geisternetz4.setMelder(melder3);

                // Transaktion starten
                em.getTransaction().begin();

                // Personen speichern
                em.persist(melder1);
                em.persist(melder2);
                em.persist(melder3);

                // Geisternetze speichern
                em.persist(geisternetz1);
                em.persist(geisternetz2);
                em.persist(geisternetz3);
                em.persist(geisternetz4);

                // Transaktion committen
                em.getTransaction().commit();

                System.out.println("Test-Geisternetze wurden erfolgreich hinzugefügt.");
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
