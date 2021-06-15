package com.example.sneakershop01.entities;
public class Products {
    private int id;
    private String name;
    private String description;
    private String imgname;
    private double price;
    private int catid;
    public Products() {
    }
    public Products(int id, String name, String description, String imgname, double price, int catid) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgname = imgname;
        this.price = price;
        this.catid = catid;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    //
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    //
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    //
    public String getImgname() {
        return imgname;
    }
    public void setImgname(String imgname) {
        this.imgname = imgname;
    }
    //
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    //
    public int getCatid() {
        return catid;
    }
    public void setCatid(int catid) {
        this.catid = catid;
    }
}
