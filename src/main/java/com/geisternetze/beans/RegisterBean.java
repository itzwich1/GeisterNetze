package com.geisternetze.beans;


import com.geisternetze.entities.Login;
import com.geisternetze.entities.Person;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Named
@RequestScoped
public class RegisterBean {

    // EntityManager für die Datenbankoperationen
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPersistenceUnit");
    private EntityManager em = emf.createEntityManager();

    private String email;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private Person.Role role;
    private int phone;

    // Getter und Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
            login.setBenutzername(username);
            login.setPasswort(password);

            Person person = new Person();
            person.setTelefonnummer(phone);
            person.setVorname(firstname);
            person.setNachname(lastname);
            person.setRolle(role);

            login.setPerson(person);

            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();


            // Beispiel: Weiterleitung zur Login-Seite nach erfolgreicher Registrierung
            return "LoginPage.xhtml?faces-redirect=true";

        }catch (Exception e) {
            e.printStackTrace();
            return null; // Fehlerfall behandeln
        }


    }

}
