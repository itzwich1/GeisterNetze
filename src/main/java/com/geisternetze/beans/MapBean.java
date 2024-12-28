package com.geisternetze.beans;

import com.geisternetze.entities.Geisternetz;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.primefaces.model.map.*;

@Named
@RequestScoped
public class MapBean {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPersistenceUnit");
    EntityManager em = emf.createEntityManager();

    private MapModel<Long> simpleModel;
    private LatLng initialCenter;

    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();

        List<Geisternetz> netze = em.createQuery(
                "SELECT g FROM Geisternetz g WHERE g.status='GEMELDET' OR g.status='BERGUNG_BEVORSTEHEND'",
                Geisternetz.class).getResultList();

        if (!netze.isEmpty()) {
            Geisternetz firstNetz = netze.get(0);
            initialCenter = new LatLng(firstNetz.getBreitengrad(), firstNetz.getLaengengrad());
        } else {
            initialCenter = new LatLng(51.1657, 10.4515); // Deutschland
        }


        for (Geisternetz netz : netze) {
            LatLng coords = new LatLng(netz.getBreitengrad(), netz.getLaengengrad());
            String description = "Netz ID: " + netz.getGeisternetzID() + ", Status: " + netz.getStatus();
            simpleModel.addOverlay(new Marker(coords, description, netz.getGeisternetzID()));
        }
    }

    public MapModel<Long> getSimpleModel() {
        return simpleModel;
    }

    public String getInitialCenter() {
        return initialCenter.getLat() + "," + initialCenter.getLng();
    }

}
