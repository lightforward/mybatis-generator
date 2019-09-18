package com.example.user.model;

import java.io.Serializable;

public class User implements Serializable {

    private  Long id;

    private  String userName;

    private String passWord;

    private String registeredTime;

    public User() {
    }

    public User(Long id, String userName, String passWord, String registeredTime) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.registeredTime = registeredTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(String registeredTime) {
        this.registeredTime = registeredTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", registeredTime='" + registeredTime + '\'' +
                '}';
    }
}
