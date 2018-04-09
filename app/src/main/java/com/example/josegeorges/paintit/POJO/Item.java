package com.example.josegeorges.paintit.POJO;

/**
 * Created by Keegan on 2018-03-25.
 */

public class Item {

    /*
    Properties
     */

    private int itemID;
    private int upc;
    private double price;
    private String description;
    private String name;
    private int imageView;

    /*
    Constructors
     */

    public Item(){

    }

    public Item(String name, int imageView) {
        this.name = name;
        this.imageView = imageView;
    }

    public Item(int itemID, int upc, double price, String description) {
        this.itemID = itemID;
        this.upc = upc;
        this.price = price;
        this.description = description;
    }

    public Item(int itemID, int upc, double price, String description, String name, int imageView) {
        this.itemID = itemID;
        this.upc = upc;
        this.price = price;
        this.description = description;
        this.name = name;
        this.imageView = imageView;
    }

    /*
            Getters and Setters
             */
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }
}
