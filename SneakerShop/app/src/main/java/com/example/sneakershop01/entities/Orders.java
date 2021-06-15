package com.example.sneakershop01.entities;
public class Orders {
    private int id;
    private int userid;
    private double amount;
    private double tax;
    private String date_order;
    public Orders() {
    }
    public Orders(int id, int userid, double amount, double tax, String date_order) {
        this.id = id;
        this.userid = userid;
        this.amount = amount;
        this.tax = tax;
        this.date_order = date_order;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    //
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    //
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    //
    public double getTax() {
        return tax;
    }
    public void setTax(double tax) {
        this.tax = tax;
    }
    //
    public String getDate_order() {
        return date_order;
    }
    public void setDate_order(String date_order) {
        this.date_order = date_order;
    }
}
