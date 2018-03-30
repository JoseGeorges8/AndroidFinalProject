package com.example.josegeorges.paintit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FavouriteColorsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FavouriteColorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavouriteColorsFragment extends Fragment {

    //for the bundle
    private static final String ARG_PARAM1 = "param1";

    //user
    private User loggedInUser;
    ArrayList<Color> favouriteColors;

    private OnFragmentInteractionListener mListener;

    //setting up recyclerView
    private RecyclerView recyclerView;


    public FavouriteColorsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user logged in user.
     * @return A new instance of fragment FavouriteColorsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavouriteColorsFragment newInstance(User user) {
        FavouriteColorsFragment fragment = new FavouriteColorsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            loggedInUser = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite_colors, container, false);

        DatabaseHandler db = new DatabaseHandler(getActivity());
        if(loggedInUser != null) {
           favouriteColors = db.getAllFavouriteColours(loggedInUser, null);
        }

        //linking recyclerView
        recyclerView = view.findViewById(R.id.favourite_colors_recyclerView);
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean supportsPredictiveItemAnimations() {

                return true;

            }
        };
        myLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setAdapter(new FavouriteColorsAdapter(favouriteColors));

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
