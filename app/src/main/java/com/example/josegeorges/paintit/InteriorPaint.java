package com.example.josegeorges.paintit;


public class InteriorPaint {
    private String paintName;
    private int paintmage;

    public InteriorPaint(String paintName, int paintmage) {
        this.paintName = paintName;
        this.paintmage = paintmage;
    }

    public String getPaintName() {
        return paintName;
    }

    public void setPaintName(String paintName) {
        this.paintName = paintName;
    }

    public int getPaintmage() {
        return paintmage;
    }

    public void setPaintmage(int paintmage) {
        this.paintmage = paintmage;
    }
}
