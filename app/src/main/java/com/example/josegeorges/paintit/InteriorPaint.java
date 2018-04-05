package com.example.josegeorges.paintit;


public class InteriorPaint {
    private String paintName;
    private int paintimage;

    public InteriorPaint(String paintName, int paintimage) {
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
