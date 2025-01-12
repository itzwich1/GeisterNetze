package com.geisternetze.services;


import com.geisternetze.entities.Geisternetz;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;

import java.util.List;

@Stateless
public class MapService {

    @PersistenceContext
    private EntityManager em;

    public List<Geisternetz> getRelevantGeisternetze() {
        return em.createQuery(
                "SELECT g FROM Geisternetz g WHERE g.status='GEMELDET'",
                Geisternetz.class).getResultList();
    }

    public LatLng getStartPosition(List<Geisternetz> netze) {
        if (!netze.isEmpty()) {
            Geisternetz firstNetz = netze.get(0);
            return new LatLng(firstNetz.getBreitengrad(), firstNetz.getLaengengrad());
        }
        return new LatLng(53.851710, 8.205024);
    }

    public void populateMarkers(List<Geisternetz> netze, org.primefaces.model.map.MapModel<Long> mapModel) {
        for (Geisternetz netz : netze) {
            LatLng coords = new LatLng(netz.getBreitengrad(), netz.getLaengengrad());
            String description = "Netz ID: " + netz.getGeisternetzID() + ", Status: " + netz.getStatus();
            mapModel.addOverlay(new Marker(coords, description, netz.getGeisternetzID()));
        }
    }

}
