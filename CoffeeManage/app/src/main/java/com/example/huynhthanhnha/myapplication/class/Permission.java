package com.example.huynhthanhnha.myapplication;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NguyenThanh on 12/11/2015.
 */
public class Permission {
    int permissionId;
    String details;
    Set<User> users;

    public Permission() {
    }

    public Permission(int permissionId, String details) {
        this.permissionId = permissionId;
        this.details = details;
        this.users = new HashSet<User>();
    }

    public Permission(int permissionId) {
        this.permissionId = permissionId;
        this.users = new HashSet<User>();
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public boolean addUser(User user){
        return this.users.add(user);
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
