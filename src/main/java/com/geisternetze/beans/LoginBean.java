package com.geisternetze.beans;

import com.geisternetze.TestData.GenerateTestGeisternetze;
import com.geisternetze.TestData.GenerateTestUsers;
import com.geisternetze.services.AuthUserLogin;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;


@Named
@RequestScoped
public class LoginBean implements Serializable {

    public LoginBean() {

        //TODO wenn Tabelle leer ist koennen hier Test user erzeugt werden
        GenerateTestUsers users = new GenerateTestUsers();
        GenerateTestGeisternetze netze = new GenerateTestGeisternetze();
    }

    @Inject
    private AuthUserLogin authUserLogin;

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

    public String doLogin(){

        boolean isAuthenticated = authUserLogin.authenticate(username,password);

        if(isAuthenticated){
            return "Dashboard.xhtml?faces-redirect=true";

        }else{

            System.out.println("Ungültige Anmeldedaten");
            return "login.xhtml";
        }



    }

}
