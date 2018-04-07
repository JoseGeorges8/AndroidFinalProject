package com.example.josegeorges.paintit.POJO;

public class Stains extends Item {
    private int stainImageView;
    private String stainName;

    public Stains(String name, int imageView, int stainImageView, String stainName) {
        super(name, imageView);
        this.stainImageView = stainImageView;
        this.stainName = stainName;
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
