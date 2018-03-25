package com.example.josegeorges.paintit;

/**
 * Created by Keegan on 2018-03-25.
 */

public class Item {
    private int itemID;
    private int upc;
    private double price;
    private String description;

    public Item(int itemID, int upc, double price, String description) {
        this.itemID = itemID;
        this.upc = upc;
        this.price = price;
        this.description = description;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getUpc() {
        return upc;
    }

    public void setUpc(int upc) {
        this.upc = upc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
