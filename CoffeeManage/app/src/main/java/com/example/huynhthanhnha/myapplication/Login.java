package com.example.huynhthanhnha.myapplication;

import com.example.huynhthanhnha.myapplication.form.Manager;
import com.example.huynhthanhnha.myapplication.form.Officer;
import com.example.huynhthanhnha.myapplication.form.User;

/**
 * Created by NguyenThanh on 24/11/2015.
 */
 public class Login {
    static String idUser = "";
    static User user = new User("thanhphi","admin", "Nguyễn Thanh Phi", "362386062");

    public static String getIdUser() {
        return idUser;
    }

    public static void setIdUser(String idUser) {
        Login.idUser = idUser;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Login.user = user;
    }
}

