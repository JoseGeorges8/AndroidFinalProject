package com.example.josegeorges.paintit.POJO;

public class ExteriorPaint {

    private String paintName;
    private int paintimage;

    public ExteriorPaint(String paintName, int paintimage) {
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


