package com.example.sneakershop01.entities;
public class Categories {
    private int id;
    private String nom;
    private String imgname;
    public Categories() {
    }
    public Categories(int id, String nom, String imgname) {
        this.id = id;
        this.nom = nom;
        this.imgname = imgname;
    }
    //
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    //
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    //
    public String getimgname() {
        return imgname;
    }
    public void setimgname(String imgname) {
        this.imgname = imgname;
    }
}
