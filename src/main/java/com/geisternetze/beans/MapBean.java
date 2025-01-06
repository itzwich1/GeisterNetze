package com.geisternetze.beans;

import com.geisternetze.entities.Geisternetz;
import com.geisternetze.services.MapService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
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

    @Inject
    private MapService mapService;

    private MapModel<Long> simpleModel;
    private LatLng initialCenter;

    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();

        List<Geisternetz> netze = mapService.getRelevantGeisternetze();

        initialCenter = mapService.getInitialCenter(netze);

        mapService.populateMarkers(netze, simpleModel);
    }

    public MapModel<Long> getSimpleModel() {
        return simpleModel;
    }

    public String getInitialCenter() {
        return initialCenter.getLat() + "," + initialCenter.getLng();
    }

}
