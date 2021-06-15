package com.example.sneakershop01.entities;
public class OrderDetails {
    private int id;
    private int orderid;
    private int productid;
    private int quantity;
    public OrderDetails() {
    }
    public OrderDetails(int id, int orderid, int productid, int quantity) {
        this.id = id;
        this.orderid = orderid;
        this.productid = productid;
        this.quantity = quantity;
    }
    public OrderDetails(int orderid, int productid, int quantity) {
        this.orderid = orderid;
        this.productid = productid;
        this.quantity = quantity;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    //
    public int getOrderid() {
        return orderid;
    }
    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
    //
    public int getProductid() {
        return productid;
    }
    public void setProductid(int productid) {
        this.productid = productid;
    }
    //
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
