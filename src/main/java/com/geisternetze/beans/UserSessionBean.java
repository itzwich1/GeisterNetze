package com.geisternetze.beans;


import com.geisternetze.entities.Person;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class UserSessionBean implements Serializable {

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    private Person person;

    public String getBenutzername() {
        return benutzername;
    }

    public String logout() {

        clearSession();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "LoginPage.xhtml?faces-redirect=true";
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    private String benutzername;


    public void clearSession() {
        this.person = null;
        this.benutzername = null;
    }

}
