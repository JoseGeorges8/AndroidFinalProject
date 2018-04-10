package com.example.josegeorges.paintit.POJO;


public class Brushes extends Item {
    private int brushImageView;
    private String brushName;

    public Brushes(int upc, double price, String description, int imageView, int size, int itemTypeId, String paintName) {
        super(upc, price, description, imageView, size, itemTypeId);
        this.brushName = paintName;
    }

    public int getBrushImageView() {
        return brushImageView;
    }

    public void setBrushImageView(int brushImageView) {
        this.brushImageView = brushImageView;
    }

    public String getBrushName() {
        return brushName;
    }

    public void setBrushName(String brushName) {
        this.brushName = brushName;
    }
}
