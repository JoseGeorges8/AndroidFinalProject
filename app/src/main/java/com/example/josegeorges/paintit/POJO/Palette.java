package com.example.josegeorges.paintit.POJO;

/**
 * Created by Keegan on 2018-03-26.
 */

public class Palette {

    private int paletteID;
    private int userID;
    private String paletteName;
    private String timestamp;

    public Palette() {
    }

    public Palette(int paletteID, int userID, String paletteName, String timestamp) {
        this.paletteID = paletteID;
        this.userID = userID;
        this.paletteName = paletteName;
        this.timestamp = timestamp;
    }

    public int getPaletteID() {
        return paletteID;
    }

    public void setPaletteID(int paletteID) {
        this.paletteID = paletteID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPaletteName() {
        return paletteName;
    }

    public void setPaletteName(String paletteName) {
        this.paletteName = paletteName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
