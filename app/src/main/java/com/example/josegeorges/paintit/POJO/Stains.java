package com.example.josegeorges.paintit.POJO;


public class Stains extends Item {
    private int stainImageView;
    private String stainName;

    public Stains(long upc, double price, String description, int imageView, int size, int itemTypeId, String paintName) {
        super(upc, price, description, imageView, size, itemTypeId);
        this.stainName = paintName;
    }

    public int getStainImageView() {
        return stainImageView;
    }

    public void setStainImageView(int stainImageView) {
        this.stainImageView = stainImageView;
    }

    public String getStainName() {
        return stainName;
    }

    public void setStainName(String stainName) {
        this.stainName = stainName;
    }
}
