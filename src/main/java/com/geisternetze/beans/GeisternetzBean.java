package com.geisternetze.beans;


import com.geisternetze.entities.Geisternetz;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Named
@RequestScoped
public class GeisternetzBean {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPersistenceUnit");
    EntityManager em = emf.createEntityManager();



    public List<Geisternetz> getGeisternetzList() {
        // JPQL-Abfrage, um alle Geisternetz-Daten zu holen
        var netze = em.createQuery("SELECT g FROM Geisternetz g", Geisternetz.class).getResultList();
        for(var netz : netze){
            System.out.println("Breitengrad: " + netz.getBreitengrad() +" , "+ netz.getErfassungsdatum()+" , "+ netz.getStatus());
        }
        return em.createQuery("SELECT g FROM Geisternetz g", Geisternetz.class).getResultList();
    }

    // Eigenschaft für das ausgewählte Geisternetz
    private Geisternetz selectedNetz;

    // Getter und Setter für das ausgewählte Geisternetz
    public Geisternetz getSelectedNetz() {
        return selectedNetz;
    }

    public void setSelectedNetz(Geisternetz selectedNetz) {
        this.selectedNetz = selectedNetz;
    }

    // Methode zum Anzeigen der Details eines ausgewählten Geisternetzes
    public void viewNetz() {
        if (selectedNetz != null) {
            // Logik zur Verarbeitung der Ansicht oder Laden zusätzlicher Daten (falls erforderlich)
            //System.out.println("Anzeigen des Geisternetzes: " + selectedNetz.getId());
        }
    }




}
