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
