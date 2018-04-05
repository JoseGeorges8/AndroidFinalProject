package com.example.josegeorges.paintit;


public class Brushes {
    private int brushImageView;
    private String brushName;

    public Brushes(int brushImageView, String brushName) {
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
