package com.geisternetze.beans;

import com.geisternetze.entities.Geisternetz;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Named
@RequestScoped
public class MapBean {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPersistenceUnit");
    EntityManager em = emf.createEntityManager();

    public List<Marker> getMarkers() {
        List<Marker> markers = new ArrayList<>();

        List<Geisternetz> netze = em.createQuery("SELECT g FROM Geisternetz g WHERE status='GEMELDET' or status='BERGUNG_BEVORSTEHEND'", Geisternetz.class).getResultList();

        for(Geisternetz netz : netze){
            markers.add(new Marker(netz.getBreitengrad(), netz.getLaengengrad(), ""));
        }
        return markers;
    }

    public String getMarkersAsJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(getMarkers());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "[]";
        }
    }

    public static class Marker {
        private double lat;
        private double lng;
        private String description;

        public Marker(double lat, double lng, String description) {
            this.lat = lat;
            this.lng = lng;
            this.description = description;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

}
