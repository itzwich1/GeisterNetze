package com.geisternetze.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loginID;

    @ManyToOne
    @JoinColumn( nullable = false)
    private Person person;

    @Column(unique = true, nullable = false, length = 50) // Maximale Länge
    private String benutzername;

    @Column(nullable = false) // Pflichtfeld
    private String passwort;

    @Column(unique = true, nullable = false)
    private String email;

    @Column( nullable = false)
    private LocalDateTime erstelltAm;


    public Login() {
    }

    // Getter und Setter
    public Long getLoginID() {
        return loginID;
    }

    public void setLoginID(Long loginID) {
        this.loginID = loginID;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

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

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public LocalDateTime getErstelltAm() {
        return erstelltAm;
    }

    public void setErstelltAm(LocalDateTime erstelltAm) {
        this.erstelltAm = erstelltAm;
    }



}
