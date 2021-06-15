package com.example.sneakershop01.entities;
public class Users {
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String telephone;
    private String password;
    public Users() {
    }
    public Users(int id, String username, String firstname, String lastname, String email, String telephone, String password) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    //
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    //
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    //
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    //
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    //
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    //
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
