package com.example.josegeorges.paintit.POJO;


public class Brushes extends Item {
    private int brushImageView;
    private String brushName;

    public Brushes(String name, int imageView, int brushImageView, String brushName) {
        super(name, imageView);
        this.brushImageView = brushImageView;
        this.brushName = brushName;
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
