package com.geisternetze.beans;

import com.geisternetze.entities.Person;
import com.geisternetze.services.GeisternetzMeldungService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@RequestScoped
public class MeldungBean implements Serializable{

    @Inject
    private GeisternetzMeldungService geisternetzMeldungService;

    @Inject
    UserSessionBean userSession;

    private boolean anonym;
    private String vorname;
    private String nachname;
    private Long telefonnummer;
    private Double breitengrad;
    private Double laengengrad;
    private Integer groesse;

    public boolean isAnonym() {
        return anonym;
    }

    public void setAnonym(boolean anonym) {
        this.anonym = anonym;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Long getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(Long telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public Double getBreitengrad() {
        return breitengrad;
    }

    public void setBreitengrad(Double breitengrad) {
        this.breitengrad = breitengrad;
    }

    public Double getLaengengrad() {
        return laengengrad;
    }

    public void setLaengengrad(Double laengengrad) {
        this.laengengrad = laengengrad;
    }

    public Integer getGroesse() {
        return groesse;
    }

    public void setGroesse(Integer groesse) {
        this.groesse = groesse;
    }

    public void addGeisternetz(){

        Person person = userSession.getPerson();

        try{
            geisternetzMeldungService.meldeGeisternetz(false,person.getVorname(),person.getNachname(),person.getTelefonnummer(),this.laengengrad,this.breitengrad, this.groesse);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void addGeisternetzOhneLogin(){
        System.out.println("MeldungBean senden");

        try{
            geisternetzMeldungService.meldeGeisternetz(this.anonym,this.vorname,this.nachname,this.telefonnummer,this.breitengrad,this.laengengrad,this.groesse);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
