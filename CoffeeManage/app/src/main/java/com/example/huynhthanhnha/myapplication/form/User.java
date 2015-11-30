package com.example.huynhthanhnha.myapplication.form;

/**
 * Created by NguyenThanh on 12/11/2015.
 */
public class User {
    String Username;
    String Password;
    String sex;
    String Name;
    String CMND;
    Permission per;

    public User(String username, String password, String name, String cmnd, String sex) {
        Username = username;
        Password = password;
        Name = name;
        this.CMND = cmnd;
        this.sex = sex;
        this.per = new Permission();
    }

    public Permission getPer() {
        return per;
    }

    public void setPer(Permission per) {
        this.per = per;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
