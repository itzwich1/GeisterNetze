package com.geisternetze.beans;

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

    private boolean anonym;
    private String vorname;
    private String nachname;
    private String telefonnummer;
    private String breitengrad;
    private String laengengrad;
    private String groesse;

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

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getBreitengrad() {
        return breitengrad;
    }

    public void setBreitengrad(String breitengrad) {
        this.breitengrad = breitengrad;
    }

    public String getLaengengrad() {
        return laengengrad;
    }

    public void setLaengengrad(String laengengrad) {
        this.laengengrad = laengengrad;
    }

    public String getGroesse() {
        return groesse;
    }

    public void setGroesse(String groesse) {
        this.groesse = groesse;
    }

    public void senden(){
        System.out.println("MeldungBean senden");


        try{
            geisternetzMeldungService.meldeGeisternetz(this.anonym,this.vorname,this.nachname,this.telefonnummer,Double.parseDouble(this.breitengrad),Double.parseDouble(this.laengengrad),Integer.parseInt(this.groesse));

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
