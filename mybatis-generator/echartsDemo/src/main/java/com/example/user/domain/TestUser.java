package com.example.user.domain;

import java.util.Date;

public class TestUser {
    private Long id;

    private String username;

    private String password;

    private Date registeredtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getRegisteredtime() {
        return registeredtime;
    }

    public void setRegisteredtime(Date registeredtime) {
        this.registeredtime = registeredtime;
    }
}