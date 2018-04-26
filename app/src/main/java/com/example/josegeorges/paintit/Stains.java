package com.example.josegeorges.paintit;

public class Stains {
    private int stainImageView;
    private String stainName;

    public Stains(int stainImageView, String stainName) {
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