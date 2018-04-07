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
    private int size;
    private int itemType;

    /*
    Constructors
     */

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

    public Item(int itemID, int upc, double price, String description, int size, int itemType) {
        this.itemID = itemID;
        this.upc = upc;
        this.price = price;
        this.description = description;
        this.size = size;
        this.itemType = itemType;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
