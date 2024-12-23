package com.geisternetze.beans;

import com.geisternetze.services.AuthUserLogin;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Iterator;

import java.io.Serializable;


@Named
@RequestScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void doLogin(){

        FacesContext context = FacesContext.getCurrentInstance();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPersistenceUnit");
        EntityManager em = emf.createEntityManager();


        AuthUserLogin userLogin;
        userLogin = new AuthUserLogin();

        /*context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Benutzername darf nicht leer sein!"));*/

        System.out.println(this.username);
        System.out.println(this.password);

    }

}
