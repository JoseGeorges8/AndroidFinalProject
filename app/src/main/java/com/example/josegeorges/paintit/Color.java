package com.example.josegeorges.paintit;

/**
 * Created by Keegan on 2018-03-25.
 */

public class Color {
    private int hexValue;
    private String colorName;
    private String timestamp;

    public Color(int hexValue, String colorName, String timestamp) {
        this.hexValue = hexValue;
        this.colorName = colorName;
        this.timestamp = timestamp;
    }

    public int getHexValue() {
        return hexValue;
    }

    public void setHexValue(int hexValue) {
        this.hexValue = hexValue;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
