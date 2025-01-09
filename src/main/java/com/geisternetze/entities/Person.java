package com.geisternetze.entities;


import jakarta.persistence.*;

@Entity
@Table(name="Person")
public class Person {

    public enum Role{
        BERGER,
        MELDER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long personID;

    @Column( nullable = false)
    private String vorname;

    @Column( )
    private String nachname;

    @Column( )
    private Long telefonnummer;

    @Enumerated(EnumType.STRING) // Speichert den Enum-Wert als String (z. B. "ADMIN", "USER")
    @Column( nullable = false)
    private Role rolle;

    public Long getPersonID() {
        return personID;
    }

    public void setPersonID(Long personenID) {
        this.personID = personenID;
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

    public Role getRolle() {
        return rolle;
    }

    public void setRolle(Role rolle) {
        this.rolle = rolle;
    }

}
