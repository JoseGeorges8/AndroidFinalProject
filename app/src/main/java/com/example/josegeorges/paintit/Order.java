package com.example.josegeorges.paintit;

/**
 * Created by Keegan on 2018-03-25.
 */

public class Order {
    private int orderID;
    private String orderNumber;
    private String dateOrdered;
    private int userID;

    public Order() {
    }

    public Order(int orderID, String orderNumber, String dateOrdered, int userID) {
        this.orderID = orderID;
        this.orderNumber = orderNumber;
        this.dateOrdered = dateOrdered;
        this.userID = userID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(String dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
