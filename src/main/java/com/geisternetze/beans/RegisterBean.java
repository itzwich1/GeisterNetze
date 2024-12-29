package com.geisternetze.beans;


import com.geisternetze.entities.Login;
import com.geisternetze.entities.Person;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

@Named
@RequestScoped
public class RegisterBean {

    // EntityManager für die Datenbankoperationen
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPersistenceUnit");
    private EntityManager em = emf.createEntityManager();

    private String email;
    private String benutzername;
    private String password;
    private String vorname;
    private String nachname;
    private Person.Role role;
    private int phone;

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

    public Person.Role getRole() {
        return role;
    }

    public void setRole(Person.Role role) {
        this.role = role;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    // Registrierungsmethode
    public String register() {
        // Hier kannst du die Logik implementieren, um die Daten in die Datenbank zu speichern

        try{

            Login login = new Login();
            login.setBenutzername(benutzername);
            login.setPasswort(password);
            login.setEmail(email);
            login.setErstelltAm(LocalDateTime.now());

            Person person = new Person();
            person.setTelefonnummer(phone);
            person.setVorname(vorname);
            person.setNachname(nachname);
            person.setRolle(role);

            login.setPerson(person);

            em.getTransaction().begin();
            em.persist(person);
            em.persist(login);
            em.getTransaction().commit();


            // Beispiel: Weiterleitung zur Login-Seite nach erfolgreicher Registrierung
            return "LoginPage.xhtml?faces-redirect=true";

        }catch (Exception e) {
            e.printStackTrace();
            return null; // Fehlerfall behandeln
        }


    }

}
