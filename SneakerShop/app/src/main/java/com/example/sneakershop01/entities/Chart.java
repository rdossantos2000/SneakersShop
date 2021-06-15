package com.example.sneakershop01.entities;
public class Chart {
    private int id;
    private int productid;
    private double price;
    private int quantity;
    private String name;
    private String description;
    private String imgname;
    public Chart() {
    }
    public Chart(int id, int productid, double price, int quantity, String name, String description, String imgname) {
        this.id = id;
        this.productid = productid;
        this.price = price;
        this.quantity = quantity;
        //
        this.name = name;
        this.description = description;
        this.imgname = imgname;
        //
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    //
    public int getProductid() {
        return productid;
    }
    public void setProductid(int productid) {
        this.productid = productid;
    }
    //
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    //
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
}
