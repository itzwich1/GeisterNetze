package com.geisternetze.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Geisternetz")
public class Geisternetz {

    public enum Status {
        GEMELDET,
        BERGUNG_BEVORSTEHEND,
        GEBORGEN,
        VERSCHOLLEN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long geisternetzID;

    @Column(nullable = false)
    private double längengrad;

    @Column(nullable = false)
    private double breitengrad;

    @Enumerated(EnumType.STRING) // Speichert den Enum-Wert als String
    @Column(nullable = false, length = 50)
    private Status status;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Person melder;

    @ManyToOne
    @JoinColumn()
    private Person berger;

    @Column(nullable = false)
    private LocalDateTime erfassungsdatum;

    @Column( )
    private LocalDateTime geborgenAm;


    // Standard-Konstruktor
    public Geisternetz() {
    }

    public Long getGeisternetzID() {
        return geisternetzID;
    }

    public void setGeisternetzID(Long geisternetzID) {
        this.geisternetzID = geisternetzID;
    }

    public double getLängengrad() {
        return längengrad;
    }

    public void setLängengrad(double längengrad) {
        this.längengrad = längengrad;
    }

    public double getBreitengrad() {
        return breitengrad;
    }

    public void setBreitengrad(double breitengrad) {
        this.breitengrad = breitengrad;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Person getMelder() {
        return melder;
    }

    public void setMelder(Person melder) {
        this.melder = melder;
    }

    public Person getBerger() {
        return berger;
    }

    public void setBerger(Person berger) {
        this.berger = berger;
    }

    public LocalDateTime getErfassungsdatum() {
        return erfassungsdatum;
    }

    public void setErfassungsdatum(LocalDateTime erfassungsdatum) {
        this.erfassungsdatum = erfassungsdatum;
    }

    public LocalDateTime getGeborgenAm() {
        return geborgenAm;
    }

    public void setGeborgenAm(LocalDateTime geborgenAm) {
        this.geborgenAm = geborgenAm;
    }


}
