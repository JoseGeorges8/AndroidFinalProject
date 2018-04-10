package com.example.josegeorges.paintit.POJO;

public class ExteriorPaint extends Item {

    private String paintName;
    private int paintimage;

    public ExteriorPaint(String name, int imageView) {
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


