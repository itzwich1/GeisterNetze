package com.geisternetze.beans;

import com.geisternetze.TestData.GenerateTestUsers;
import com.geisternetze.services.AuthUserLogin;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;


@Named
@RequestScoped
public class LoginBean implements Serializable {

    public LoginBean() {

        //TODO wenn Tabelle leer ist koennen hier Test user erzeugt werden
        //GenerateTestUsers users = new GenerateTestUsers();
    }

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

        AuthUserLogin userLogin;
        userLogin = new AuthUserLogin();

        /*context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Benutzername darf nicht leer sein!"));*/

        System.out.println(this.username);
        System.out.println(this.password);

    }

}
