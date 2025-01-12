package com.geisternetze.beans;


import com.geisternetze.entities.Geisternetz;
import com.geisternetze.entities.Person;
import com.geisternetze.services.GeisternetzService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class GeisternetzBean {

    @Inject
    private GeisternetzService geisternetzService;

    @Inject
    UserSessionBean userSession;

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

    // Getter und Setter für das ausgewählte Geisternetz
    public Geisternetz getSelectedNetz() {
        return selectedNetz;
    }

    public void setSelectedNetz(Geisternetz selectedNetz) {
        this.selectedNetz = selectedNetz;
    }

    public List<Geisternetz> getGeisternetzList() {
        return geisternetzService.getGeisternetzList();
    }

    public List<Geisternetz> getFilteredGeisternetzList() {
        return geisternetzService.getFilteredGeisternetzList(filterStatus);
    }

    //Liefert die Geisternetze mit dem Status gemaeldet
    public List<Geisternetz> getGemeldetGeisternetzList() {
        return geisternetzService.getFilteredGeisternetzList("GEMELDET");
    }

    // Methode zum Anzeigen der Details eines ausgewählten Geisternetzes
    public void viewNetz() {
        if (selectedNetz != null) {
            // Logik zur Verarbeitung der Ansicht oder Laden zusätzlicher Daten (falls erforderlich)
            //System.out.println("Anzeigen des Geisternetzes: " + selectedNetz.getId());
        }
    }

    public void netzGeborgen(){

        if (selectedNetz != null) {
            geisternetzService.geborgenHinterlegen(selectedNetz);
        }

    }

    public void fuerBergungEintragen(){

        if (selectedNetz != null) {
            geisternetzService.bergungAngekuendigt (selectedNetz, userSession.getPerson());
        }

    }

    public void netzVerschollen(){
        if(selectedNetz != null){

            geisternetzService.netzVerschollenHinterlegen(selectedNetz);
        }
    }

}
