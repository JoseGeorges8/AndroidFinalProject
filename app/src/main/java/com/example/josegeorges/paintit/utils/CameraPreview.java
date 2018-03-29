package com.example.josegeorges.paintit.utils;

/**
 * Created by josegeorges on 2018-03-28.
 */


import android.content.Context;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.TextureView;

import java.io.IOException;

/** A basic Camera preview class */
public class CameraPreview extends TextureView implements TextureView.SurfaceTextureListener, Camera.PreviewCallback {
    private SurfaceHolder mHolder;
    private Camera mCamera;

    //instance of my interface
    private OnColorSelectedListener mOnColorSelectedListener;

    //getter for interface
    public OnColorSelectedListener getmOnColorSelectedListener() {
        return mOnColorSelectedListener;
    }

    //setter for interface
    public void setmOnColorSelectedListener(OnColorSelectedListener mOnColorSelectedListener) {
        this.mOnColorSelectedListener = mOnColorSelectedListener;
    }

    //size of the pointer where the color is being grabbed from
    protected static final int POINTER_RADIUS = 5;

    //array of 3 colors for the rgb values when selecting a color
    protected int[] mSelectedColor;

    //camera size
    protected Camera.Size mPreviewSize;


    public static final String TAG = "CAMERATEST";

    public CameraPreview(Context context, Camera camera) {
        super(context);
        mCamera = camera;


        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        this.setSurfaceTextureListener(this);

        mPreviewSize = mCamera.getParameters().getPreviewSize();
        mSelectedColor = new int[3];
    }


    /**
     * Has the documentation states "Callback interface used to deliver copies of preview frames as they are displayed."
     *
     * @param camera camera being used to display this
     */
    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        if (mOnColorSelectedListener != null) {
            final int midX = mPreviewSize.width / 2;
            final int midY = mPreviewSize.height / 2;

            // Reset the selected color.
            mSelectedColor[0] = 0;
            mSelectedColor[1] = 0;
            mSelectedColor[2] = 0;

            // Compute the average selected color.
            for (int i = 0; i <= POINTER_RADIUS; i++) {
                for (int j = 0; j <= POINTER_RADIUS; j++) {
                    addColorFromYUV420(data, mSelectedColor, (i * POINTER_RADIUS + j + 1),
                            (midX - POINTER_RADIUS) + i, (midY - POINTER_RADIUS) + j,
                            mPreviewSize.width, mPreviewSize.height);
                }
            }

            mOnColorSelectedListener.onColorSelected(Color.rgb(mSelectedColor[0], mSelectedColor[1], mSelectedColor[2]));
        }
    }

    protected void addColorFromYUV420(byte[] data, int[] averageColor, int count, int x, int y, int width, int height) {
        // The code converting YUV420 to rgb format is highly inspired from this post http://stackoverflow.com/a/10125048
        final int size = width * height;
        final int Y = data[y * width + x] & 0xff;
        final int xby2 = x / 2;
        final int yby2 = y / 2;

        final float V = (float) (data[size + 2 * xby2 + yby2 * width] & 0xff) - 128.0f;
        final float U = (float) (data[size + 2 * xby2 + 1 + yby2 * width] & 0xff) - 128.0f;

        // Do the YUV -> RGB conversion
        float Yf = 1.164f * ((float) Y) - 16.0f;
        int red = (int) (Yf + 1.596f * V);
        int green = (int) (Yf - 0.813f * V - 0.391f * U);
        int blue = (int) (Yf + 2.018f * U);

        // Clip rgb values to [0-255]
        red = red < 0 ? 0 : red > 255 ? 255 : red;
        green = green < 0 ? 0 : green > 255 ? 255 : green;
        blue = blue < 0 ? 0 : blue > 255 ? 255 : blue;

        averageColor[0] += (red - averageColor[0]) / count;
        averageColor[1] += (green - averageColor[1]) / count;
        averageColor[2] += (blue - averageColor[2]) / count;

    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        // The Surface has been created, now tell the camera where to draw the preview.
        try {
            mCamera.setPreviewTexture(surfaceTexture);
            mCamera.setPreviewCallback(this);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }

    /**
     * Interface to communicate to an activity
     */
    public interface OnColorSelectedListener{
        void onColorSelected(int color);
    }
}