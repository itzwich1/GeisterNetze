package com.geisternetze.services;



import jakarta.ejb.Stateless;
import jakarta.enterprise.context.RequestScoped;

import java.sql.*;



@RequestScoped
public class AuthUserLogin {



    public AuthUserLogin()  {

        //Datenbank test
        /*String url = "jdbc:postgresql://localhost:5432/postgres"; // Ersetze durch deine Datenbank-URL
        String username = "postgres";                        // Ersetze durch deinen Benutzernamen
        String password = "felixdocker!";

        try(Connection connection = DriverManager.getConnection(url, username,password)){
            if (connection != null) {
                System.out.println("Verbindung zur Datenbank erfolgreich!");

                Statement statement = connection.createStatement();

                String sql = "SELECT * FROM test"; // Deine Tabelle
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    int test1 = resultSet.getInt("test1");
                    int test2 = resultSet.getInt("test2");
                    int test3 = resultSet.getInt("test3");

                    System.out.println("LoginID: " + test1);
                    System.out.println("Benutzername: " + test2);
                    System.out.println("Passwort: " + test3);
                    System.out.println("--------------------");
                }


            } else {
                System.out.println("Verbindung zur Datenbank fehlgeschlagen!");
            }
        }catch (SQLException e) {
            System.err.println("Fehler bei der Verbindung zur Datenbank: " + e.getMessage());
            e.printStackTrace();
        }*/

        System.out.println("AuthUserLogin");
        //Hier die initialisierung des User durchführen
    }




}
