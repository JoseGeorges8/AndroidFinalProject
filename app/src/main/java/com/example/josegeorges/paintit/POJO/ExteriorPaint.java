package com.example.josegeorges.paintit.POJO;

public class ExteriorPaint extends Item {

    private String paintName;
    private int paintimage;

    public ExteriorPaint(long upc, double price, String description, int imageView, int size, int itemTypeId, String paintName) {
        super(upc, price, description, imageView, size, itemTypeId);
        this.paintName = paintName;
    }
    
    public String getPaintName() {
        return paintName;
    }

    public void setPaintName(String paintName) {
        this.paintName = paintName;
    }

    public int getPaintimage() {
        return paintimage;
    }

    public void setPaintimage(int paintimage) {
        this.paintimage = paintimage;
    }
}


