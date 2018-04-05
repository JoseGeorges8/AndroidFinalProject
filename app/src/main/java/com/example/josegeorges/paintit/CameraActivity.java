package com.example.josegeorges.paintit;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.josegeorges.paintit.utils.CameraHelper;
import com.example.josegeorges.paintit.utils.CameraPreview;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener, CameraPreview.OnColorSelectedListener {

    public static final String SELECTED_COLOR = "color_selected";



    Toolbar toolbar;

    private Camera mCamera; //My camera object
    private CameraPreview mPreview; //Custom textureView to display the FPS from the camera
    private CameraAsyncTask mCameraAsyncTask; //AsyncTask to work the camera on a background thread

    protected FrameLayout preview; //The view that holds my preview
    protected boolean mIsPortrait; //boolean to check orientation
    protected int mSelectedColor; //selected color continousy
    protected int mLastSelectedColor; //color clicked by the user

    protected TextView mTextView; //textview where the selected color appears
    protected Button mButton; //button to confirm selected color

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //set up the needed views
        mTextView = findViewById(R.id.last_selected_color);
        preview = findViewById(R.id.camera_preview);
        mButton = findViewById(R.id.button_confirm_camera_color);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(SELECTED_COLOR, mLastSelectedColor);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        //checking for orientation
        mIsPortrait = getResources().getBoolean(R.bool.is_portrait);


    }

    @Override
    protected void onResume() {
        super.onResume();
        // Setup the camera asynchronously.
        mCameraAsyncTask = new CameraAsyncTask();
        mCameraAsyncTask.execute();
    }


    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Cancel the Camera AsyncTask.
        mCameraAsyncTask.cancel(true);

        // Release the camera.
        releaseCamera();

        // Remove the camera preview
        if (mPreview != null) {
            preview.removeView(mPreview);
        }
    }

    private void releaseCamera(){
        if (mCamera != null){
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }


    /**
     * This is just awesome
     * @param view
     */
    @Override
    public void onClick(View view) {
        //here we catch the color
        if (view == mPreview){
            mLastSelectedColor = mSelectedColor;
            mTextView.setText("#" + Integer.toHexString(mLastSelectedColor).substring(2));
        }

        if(view == mButton){
            //for some reason this does not work with the button
            Log.d("SELECTED", "button clicked");
        }
    }

    /**
     * This is awesome as well
     * @param color being analized by the preview.
     *
     * This is the implemented method from our interface
     */
    @Override
    public void onColorSelected(int color) {
        mSelectedColor = color;
    }


    /**
     * It is a good practice to get the camera in a separate threat from the UI threat so it doesnt mess up the UI
     */
    private class CameraAsyncTask extends AsyncTask<Void, Void, Camera> {

        /**
         * The LayoutParams used for adding the preview to its container.
         */
        protected FrameLayout.LayoutParams mPreviewParams;

        @Override
        protected Camera doInBackground(Void... params) {
            Camera camera = getCameraInstance();
            if (camera == null) {
                CameraActivity.this.finish();
                return null;
            }
            //configure Camera parameters
            Camera.Parameters cameraParameters = camera.getParameters();

            //get optimal camera preview size according to the layout used to display it
            Camera.Size bestSize = CameraHelper.getBestPreviewSize(
                    cameraParameters.getSupportedPreviewSizes()
                    , preview.getWidth()
                    , preview.getHeight()
                    , mIsPortrait);

            //set optimal camera preview
            cameraParameters.setPreviewSize(bestSize.width, bestSize.height);
            camera.setParameters(cameraParameters);

            //set camera orientation to match with current device orientation
            CameraHelper.setCameraDisplayOrientation(CameraActivity.this, camera);

            //get proportional dimension for the layout used to display preview according to the preview size used
            int[] adaptedDimension = CameraHelper.getProportionalDimension(
                    bestSize
                    , preview.getWidth()
                    , preview.getHeight()
                    , mIsPortrait);



            //set up params for the layout used to display the preview
            mPreviewParams = new FrameLayout.LayoutParams(adaptedDimension[0], adaptedDimension[1]);
            mPreviewParams.gravity = Gravity.CENTER;

            return camera;
        }

        @Override
        protected void onPostExecute(Camera camera) {
            super.onPostExecute(camera);

            // Check if the task is cancelled before trying to use the camera.
            if (!isCancelled()) {
                mCamera = camera;
                if (mCamera == null) {
                    CameraActivity.this.finish();
                } else {

                    // Create our Preview view and set it as the content of our activity.
                    mPreview = new CameraPreview(CameraActivity.this, mCamera);
                    mPreview.setmOnColorSelectedListener(CameraActivity.this);
                    mPreview.setOnClickListener(CameraActivity.this);

                    preview.addView(mPreview, 0, mPreviewParams);
                }
            }
        }

        @Override
        protected void onCancelled(Camera camera) {
            super.onCancelled(camera);
            if (camera != null) {
                camera.release();
            }
        }
    }
}
