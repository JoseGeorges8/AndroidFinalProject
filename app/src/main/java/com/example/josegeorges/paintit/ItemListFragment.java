package com.example.josegeorges.paintit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.example.josegeorges.paintit.POJO.Item;
import com.example.josegeorges.paintit.adapters.ItemsAdapter;

import java.util.ArrayList;


public class ItemListFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    private ArrayList<Item> listReceived;

    private OnFragmentInteractionListener mListener;

    FragmentManager fm;

    public ItemListFragment() {
        // Required empty public constructor
    }

    public static ItemListFragment newInstance(ArrayList<Item> list) {
        ItemListFragment fragment = new ItemListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listReceived = getArguments().getParcelableArrayList(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

//        ColorPickerActivity.fab.hide();

//
//        // List of Exterior Paints
//        exteriorPaintList.add(new ExteriorPaint("Exterior Flat", R.drawable.ic_camera_enhance_black_24dp));
//        exteriorPaintList.add(new ExteriorPaint("Exterior Satin Enamel", R.drawable.ic_camera_enhance_black_24dp));
//        exteriorPaintList.add(new ExteriorPaint("Exterior Semi-Gloss Enamel", R.drawable.ic_camera_enhance_black_24dp));
//        exteriorPaintList.add(new ExteriorPaint("Exterior Gloss Enamel", R.drawable.ic_camera_enhance_black_24dp));
//        exteriorPaintList.add(new ExteriorPaint("Exterior Matte", R.drawable.ic_camera_enhance_black_24dp));
//
//        //                //Get an instance of the database
//        //                DatabaseHandler db = new DatabaseHandler(getContext());
//        //                //Add the interior paints to the database
//        //                db.addItem(exteriorPaintList);
//        //                //Close the database
//        //                db.close();
//        //                //Grab the fragment manager and move us back a page/fragment
//        //                fm = getActivity().getSupportFragmentManager();
//        //                fm.popBackStack();
//
//
//        // List of Stains
//        stainsList.add(new Stains("Hardwood Finish", R.drawable.ic_camera_enhance_black_24dp));
//        stainsList.add(new Stains("Clearwood Finish", R.drawable.ic_camera_enhance_black_24dp));
//        stainsList.add(new Stains("Semi-Transparent Oil Finish", R.drawable.ic_camera_enhance_black_24dp));
//        stainsList.add(new Stains("Semi-Transparent Deck Stain", R.drawable.ic_camera_enhance_black_24dp));
//        stainsList.add(new Stains("Solid Deck Stain", R.drawable.ic_camera_enhance_black_24dp));
//        stainsList.add(new Stains("All Purpose Deck Wash", R.drawable.ic_camera_enhance_black_24dp));
//        stainsList.add(new Stains("Stain Remover", R.drawable.ic_camera_enhance_black_24dp));
//
//        //                //Get an instance of the database
//        //                DatabaseHandler db = new DatabaseHandler(getContext());
//        //                //Add the interior paints to the database
//        //                db.addItem(stainsList);
//        //                //Close the database
//        //                db.close();
//        //                //Grab the fragment manager and move us back a page/fragment
//        //                fm = getActivity().getSupportFragmentManager();
//        //                fm.popBackStack();
//
//        // List of Brushes
//        brushesList.add(new Brushes("Round Brush", R.drawable.ic_camera_enhance_black_24dp));
//        brushesList.add(new Brushes("Flat Brush", R.drawable.ic_camera_enhance_black_24dp));
//        brushesList.add(new Brushes("Angular Brush", R.drawable.ic_camera_enhance_black_24dp));
//        brushesList.add(new Brushes("Bright Brush", R.drawable.ic_camera_enhance_black_24dp));
//        brushesList.add(new Brushes("Filbert Brush", R.drawable.ic_camera_enhance_black_24dp));
//        brushesList.add(new Brushes("Fan Brush", R.drawable.ic_camera_enhance_black_24dp));
//        itemsList.add(new Item("Roller", R.drawable.ic_camera_enhance_black_24dp));
//
//
//        //                //Get an instance of the database
//        //                DatabaseHandler db = new DatabaseHandler(getContext());
//        //                //Add the interior paints to the database
//        //                db.addItem(brushesList);
//        //                //Close the database
//        //                db.close();
//        //                //Grab the fragment manager and move us back a page/fragment
//        //                fm = getActivity().getSupportFragmentManager();
//        //                fm.popBackStack();


        /*
        All we need to do here is basically inflate the recycler view, along with passing the adapter and the list received from the
         new instance method
         */

        if(listReceived != null) {
            RecyclerView recyclerView = view.findViewById(R.id.item_recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new ItemsAdapter(listReceived, getContext()));
        }

        //TODO Animate the Recycler View Skip For Now
//        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
//        itemAnimator.setAddDuration(1000);
//        itemAnimator.setRemoveDuration(1000);
//        recyclerView.setItemAnimator(itemAnimator);



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
