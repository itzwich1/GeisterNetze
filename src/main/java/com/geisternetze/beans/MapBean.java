package com.geisternetze.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Named
@RequestScoped
public class MapBean {

    public List<Marker> getMarkers() {
        List<Marker> markers = new ArrayList<>();


        //54.352951, 6.132732
        //58.168888, 17.943035
        //50.141270, -0.790834
        //38.382651, 5.712518
        markers.add(new Marker(-20.0, 90.0, "Indischer Ozean"));
        markers.add(new Marker(-15.0, -140.0, "Pazifischer Ozean"));
        markers.add(new Marker(40.7128, -74.0060, "New York"));
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
