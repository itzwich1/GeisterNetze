package com.geisternetze.beans;


import com.geisternetze.entities.Person;
import com.geisternetze.services.RegisterService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class RegisterBean {

    @Inject
    private RegisterService registerService;

    private String email;
    private String benutzername;
    private String password;
    private String vorname;
    private String nachname;
    private Person.Role rolle;
    private String telefonnummer;

    // Getter und Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Person.Role getRolle() {
        return rolle;
    }

    public void setRolle(Person.Role rolle) {
        this.rolle = rolle;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    // Registrierungsmethode
    public String register() {

        try{
            registerService.registerUser(benutzername, password, email, vorname, nachname, rolle, telefonnummer);
            return "LoginPage.xhtml?faces-redirect=true";

        }catch (Exception e) {
            e.printStackTrace();
            return null; // Fehlerfall behandeln
        }


    }

}
