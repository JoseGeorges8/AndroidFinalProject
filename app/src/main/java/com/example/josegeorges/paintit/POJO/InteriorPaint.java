package com.example.josegeorges.paintit.POJO;


public class InteriorPaint extends Item {
    private String paintName;
    private int paintimage;

    public InteriorPaint(String name, int imageView) {
        super(name, imageView);
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


