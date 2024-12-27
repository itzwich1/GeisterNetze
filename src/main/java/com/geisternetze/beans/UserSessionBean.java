package com.geisternetze.beans;


import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class UserSessionBean implements Serializable {

    private Long userId;
    private String username;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void clearSession() {
        this.userId = null;
        this.username = null;
    }

}
