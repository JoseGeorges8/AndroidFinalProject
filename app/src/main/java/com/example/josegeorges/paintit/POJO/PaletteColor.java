package com.example.josegeorges.paintit.POJO;

import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by josegeorges on 2018-03-23.
 */

public class PaletteColor {

    private FrameLayout frame;
    private ImageView deleteIcon;



    public FrameLayout getFrame() {
        return frame;
    }

    public void setFrame(FrameLayout frame) {
        this.frame = frame;
    }

    public ImageView getDeleteIcon() {
        return deleteIcon;
    }

    public void setDeleteIcon(ImageView deleteIcon) {
        this.deleteIcon = deleteIcon;
    }
}
