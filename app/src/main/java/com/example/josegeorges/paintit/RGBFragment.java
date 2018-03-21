package com.example.josegeorges.paintit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;


/**

    Fragment will serve to pass the values from the SeekBars onto the Activity

 */
public class RGBFragment extends Fragment {

    //keys to identify colors while passing them to the activity
    public static final String RED = "red";
    public static final String GREEN = "green";
    public static final String BLUE = "blue";

    //listener
    private OnFragmentInteractionListener mListener;

    //RGB Bars
    SeekBar redBar;
    SeekBar greenBar;
    SeekBar blueBar;

    //RGB TextViews
    TextView redTextView;

    public RGBFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rgb, container, false);


        //views
        redBar = view.findViewById(R.id.red_value);
        greenBar = view.findViewById(R.id.green_value);
        blueBar = view.findViewById(R.id.blue_value);
        redTextView = view.findViewById(R.id.red_textview);

        //max value in RGB is 255
        redBar.setMax(255);
        greenBar.setMax(255);
        blueBar.setMax(255);

        //listeners to update value while the SeekBars position changes

        redBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                onSeekBarChange(i, RED);
                redTextView.setText(""+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        greenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                onSeekBarChange(i, GREEN);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        blueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //pass the value to the activity
                onSeekBarChange(i, BLUE);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        return view;
    }

    /**
     * Calls the method from the interface and passes the values to it
     * @param value color value from the seekbar moved
     * @param key so the activity knows which bar is.
     */
    public void onSeekBarChange(int value, String key) {
        if (mListener != null) {
            mListener.onFragmentInteraction(value, key);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int value, String key);
    }
}
