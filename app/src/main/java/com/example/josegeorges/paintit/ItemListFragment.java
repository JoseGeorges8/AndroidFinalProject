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

import com.example.josegeorges.paintit.POJO.Brushes;
import com.example.josegeorges.paintit.POJO.ExteriorPaint;
import com.example.josegeorges.paintit.POJO.InteriorPaint;
import com.example.josegeorges.paintit.POJO.Item;
import com.example.josegeorges.paintit.POJO.Stains;
import com.example.josegeorges.paintit.adapters.ItemsAdapter;
import com.example.josegeorges.paintit.utils.DatabaseHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ItemListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItemListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ArrayList<InteriorPaint> interiorPaintList;
    ArrayList<ExteriorPaint> exteriorPaintList;
    ArrayList<Stains> stainsList;
    ArrayList<Brushes> brushesList;
    ArrayList<Item> itemsList;


    FragmentManager fm;

    public ItemListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemListFragment newInstance(String param1, String param2) {
        ItemListFragment fragment = new ItemListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        ColorPickerActivity.fab.hide();

        // List of Interior Paints
        interiorPaintList.add(new InteriorPaint("Acrylic Flat", R.drawable.ic_camera_enhance_black_24dp));
        interiorPaintList.add(new InteriorPaint("Acrylic Eggshell", R.drawable.ic_camera_enhance_black_24dp));
        interiorPaintList.add(new InteriorPaint("Acrylic Satin", R.drawable.ic_camera_enhance_black_24dp));
        interiorPaintList.add(new InteriorPaint("Acrylic Semi-Gloss", R.drawable.ic_camera_enhance_black_24dp));
        interiorPaintList.add(new InteriorPaint("Acrylic Gloss", R.drawable.ic_camera_enhance_black_24dp));
        interiorPaintList.add(new InteriorPaint("Alkyed Flat", R.drawable.ic_camera_enhance_black_24dp));
        interiorPaintList.add(new InteriorPaint("Alkyed Semi-Gloss", R.drawable.ic_camera_enhance_black_24dp));
        interiorPaintList.add(new InteriorPaint("Alyked Gloss", R.drawable.ic_camera_enhance_black_24dp));

        //                //Get an instance of the database
        //                DatabaseHandler db = new DatabaseHandler(getContext());
        //                //Add the interior paints to the database
        //                db.addItem(interiorPaintList);
        //                //Close the database
        //                db.close();
        //                //Grab the fragment manager and move us back a page/fragment
        //                fm = getActivity().getSupportFragmentManager();
        //                fm.popBackStack();

        // List of Exterior Paints
        exteriorPaintList.add(new ExteriorPaint("Exterior Flat", R.drawable.ic_camera_enhance_black_24dp));
        exteriorPaintList.add(new ExteriorPaint("Exterior Satin Enamel", R.drawable.ic_camera_enhance_black_24dp));
        exteriorPaintList.add(new ExteriorPaint("Exterior Semi-Gloss Enamel", R.drawable.ic_camera_enhance_black_24dp));
        exteriorPaintList.add(new ExteriorPaint("Exterior Gloss Enamel", R.drawable.ic_camera_enhance_black_24dp));
        exteriorPaintList.add(new ExteriorPaint("Exterior Matte", R.drawable.ic_camera_enhance_black_24dp));

        //                //Get an instance of the database
        //                DatabaseHandler db = new DatabaseHandler(getContext());
        //                //Add the interior paints to the database
        //                db.addItem(exteriorPaintList);
        //                //Close the database
        //                db.close();
        //                //Grab the fragment manager and move us back a page/fragment
        //                fm = getActivity().getSupportFragmentManager();
        //                fm.popBackStack();


        // List of Stains
        stainsList.add(new Stains("Hardwood Finish", R.drawable.ic_camera_enhance_black_24dp));
        stainsList.add(new Stains("Clearwood Finish", R.drawable.ic_camera_enhance_black_24dp));
        stainsList.add(new Stains("Semi-Transparent Oil Finish", R.drawable.ic_camera_enhance_black_24dp));
        stainsList.add(new Stains("Semi-Transparent Deck Stain", R.drawable.ic_camera_enhance_black_24dp));
        stainsList.add(new Stains("Solid Deck Stain", R.drawable.ic_camera_enhance_black_24dp));
        stainsList.add(new Stains("All Purpose Deck Wash", R.drawable.ic_camera_enhance_black_24dp));
        stainsList.add(new Stains("Stain Remover", R.drawable.ic_camera_enhance_black_24dp));

        //                //Get an instance of the database
        //                DatabaseHandler db = new DatabaseHandler(getContext());
        //                //Add the interior paints to the database
        //                db.addItem(stainsList);
        //                //Close the database
        //                db.close();
        //                //Grab the fragment manager and move us back a page/fragment
        //                fm = getActivity().getSupportFragmentManager();
        //                fm.popBackStack();

        // List of Brushes
        brushesList.add(new Brushes("Round Brush", R.drawable.ic_camera_enhance_black_24dp));
        brushesList.add(new Brushes("Flat Brush", R.drawable.ic_camera_enhance_black_24dp));
        brushesList.add(new Brushes("Angular Brush", R.drawable.ic_camera_enhance_black_24dp));
        brushesList.add(new Brushes("Bright Brush", R.drawable.ic_camera_enhance_black_24dp));
        brushesList.add(new Brushes("Filbert Brush", R.drawable.ic_camera_enhance_black_24dp));
        brushesList.add(new Brushes("Fan Brush", R.drawable.ic_camera_enhance_black_24dp));
        itemsList.add(new Item("Roller", R.drawable.ic_camera_enhance_black_24dp));


        //                //Get an instance of the database
        //                DatabaseHandler db = new DatabaseHandler(getContext());
        //                //Add the interior paints to the database
        //                db.addItem(brushesList);
        //                //Close the database
        //                db.close();
        //                //Grab the fragment manager and move us back a page/fragment
        //                fm = getActivity().getSupportFragmentManager();
        //                fm.popBackStack();


        RecyclerView.Adapter adapter;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.item_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ItemsAdapter(itemsList, getContext());

        recyclerView.setAdapter(adapter);


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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
