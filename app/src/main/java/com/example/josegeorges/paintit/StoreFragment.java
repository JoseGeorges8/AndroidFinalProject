package com.example.josegeorges.paintit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.josegeorges.paintit.POJO.Item;
import com.example.josegeorges.paintit.utils.DatabaseHandler;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StoreFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    public StoreFragment() {
        // Required empty public constructor
    }

    public static StoreFragment newInstance(String param1, String param2) {
        StoreFragment fragment = new StoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.paint_types_large, container, false);

        /*
        Basically, when the button is pressed, we search for the type id that is supposed to

        types available: interior paints, exterior paints, stains and brushes

        we look for the list and pass it through the new instance method to the item list fragment
         */

        // INTERIOR PAINTS BUTTON
        Button interiorPaintsButton = view.findViewById(R.id.interiorPaintButton);
        interiorPaintsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());
                ArrayList<Item> interiorPaints = db.getItems("0", "3");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_content, ItemListFragment.newInstance(interiorPaints))
                        .addToBackStack(null)
                        .commit();
            }
        });

        // EXTERIOR PAINTS BUTTON
        Button exteriorPaintsButton = view.findViewById(R.id.exteriorPaintButton);
        exteriorPaintsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());
                ArrayList<Item> exteriorPaints = db.getItems("1", "3");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_content, ItemListFragment.newInstance(exteriorPaints))
                        .addToBackStack(null)
                        .commit();
            }
        });

        // STAINS BUTTON
        Button stainsButton = view.findViewById(R.id.stainButton);
        stainsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());
                ArrayList<Item> stainsList = db.getItems("2", "0");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_content, ItemListFragment.newInstance(stainsList))
                        .addToBackStack(null)
                        .commit();
            }
        });

        // BRUSHES BUTTON
        Button brushesButton = view.findViewById(R.id.brushesButton);
        brushesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());
                ArrayList<Item> brushesList = db.getItems("3", "0");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_content, ItemListFragment.newInstance(brushesList))
                        .addToBackStack(null)
                        .commit();
            }
        });


        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
