package com.geisternetze.beans;

import com.geisternetze.TestData.GenerateTestGeisternetze;
import com.geisternetze.TestData.GenerateTestUsers;
import com.geisternetze.entities.Person;
import com.geisternetze.services.AuthUserLoginService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;


@Named
@RequestScoped
public class LoginBean implements Serializable {


    @Inject
    private UserSessionBean userSession;

    public LoginBean() {
        GenerateTestUsers users = new GenerateTestUsers();
        GenerateTestGeisternetze netze = new GenerateTestGeisternetze();
    }

    @Inject
    private AuthUserLoginService authUserLoginService;

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

        boolean isAuthenticated = authUserLoginService.authenticate(username,password);

        if(isAuthenticated){

            System.out.println(userSession.getPerson().getRolle().toString());
            if(userSession.getPerson().getRolle() == Person.Role.BERGER){
                return "DashboardBerger.xhtml?faces-redirect=true";
            }else if(userSession.getPerson().getRolle() == Person.Role.MELDER){
                return "DashboardMelder.xhtml?faces-redirect=true";
            }else{
                System.out.println("Undefinierte Rolle");
                return "login.xhtml";
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ungültige Anmeldedaten",""));
            System.out.println("Ungültige Anmeldedaten");
            return "login.xhtml";
        }
    }
}
