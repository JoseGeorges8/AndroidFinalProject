package com.example.josegeorges.paintit.POJO;


public class InteriorPaint extends Item {
    private String paintName;
    private int paintimage;

    public InteriorPaint(String name, int imageView, String paintName, int paintimage) {
        super(name, imageView);
        this.paintName = paintName;
        this.paintimage = paintimage;
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


