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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class GeisternetzBean {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPersistenceUnit");
    EntityManager em = emf.createEntityManager();


    // Filterkriterium für den Status
    private String filterStatus = "";


    // Eigenschaft für das ausgewählte Geisternetz
    private Geisternetz selectedNetz;

    // Getter und Setter für Filterstatus
    public String getFilterStatus() {
        return filterStatus;
    }

    public void setFilterStatus(String filterStatus) {
        this.filterStatus = filterStatus;
    }

    public List<Geisternetz> getGeisternetzList() {
        return em.createQuery("SELECT g FROM Geisternetz g", Geisternetz.class).getResultList();
    }

    public List<Geisternetz> getFilteredGeisternetzList() {
        if (filterStatus == null || filterStatus.isEmpty()) {
            return getGeisternetzList(); // Keine Filterung, alle Einträge anzeigen
        }
        return getGeisternetzList().stream()
                .filter(netz -> netz.getStatus().name().equals(filterStatus))
                .collect(Collectors.toList());
    }

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

    public void netzGeborgen(){
        System.out.println("Netz Geborgen");
        System.out.println(selectedNetz);

        selectedNetz.setStatus(Geisternetz.Status.GEBORGEN);
        selectedNetz.setGeborgenAm(LocalDateTime.now());

        em.getTransaction().begin();

        em.persist(selectedNetz);

        em.getTransaction().commit();

    }

}
