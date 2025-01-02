package com.geisternetze.services;


import com.geisternetze.entities.Geisternetz;
import com.geisternetze.entities.Person;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class GeisternetzService {

    @PersistenceContext
    private EntityManager em;

    public List<Geisternetz> getGeisternetzList() {
        return em.createQuery("SELECT g FROM Geisternetz g", Geisternetz.class).getResultList();
    }

    public List<Geisternetz> getFilteredGeisternetzList(String filterStatus) {
        if (filterStatus == null || filterStatus.isEmpty()) {
            return getGeisternetzList(); // Keine Filterung, alle Einträge anzeigen
        }
        return em.createQuery("SELECT g FROM Geisternetz g WHERE g.status = :status", Geisternetz.class)
                .setParameter("status", Geisternetz.Status.valueOf(filterStatus))
                .getResultList();
    }

    public void markAsGeborgen(Geisternetz geisternetz) {
        geisternetz.setStatus(Geisternetz.Status.GEBORGEN);
        geisternetz.setGeborgenAm(LocalDateTime.now());
        em.merge(geisternetz); // Update der Daten
    }

    public void assignBerger(Geisternetz geisternetz, Long userId) {
        Person berger = em.find(Person.class, userId);

        geisternetz.setBerger(berger);
        geisternetz.setStatus(Geisternetz.Status.BERGUNG_BEVORSTEHEND);
        em.merge(geisternetz); // Update der Daten
    }

}
