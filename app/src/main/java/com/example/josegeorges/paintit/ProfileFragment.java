package com.example.josegeorges.paintit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    //for the bundle
    private static final String ARG_PARAM1 = "param1";

    //user
    private User loggedInUser;
    ArrayList<Color> favouriteColors;

    private OnFragmentInteractionListener mListener;

    //these two go together
    private RecyclerView favouriteColoursRecyclerView;
    private TextView seeAllFavColors;

    //these two go together
    private TextView noColors;
    private Button addFavColor;

    private SwipeRefreshLayout swipeRefreshLayout;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user logged in user.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(User user) {
        ProfileFragment fragment = new ProfileFragment();
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
            Log.d("USER", loggedInUser.getEmail() + "it's now on its profile");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {



                swipeRefreshLayout.setRefreshing(false);
            }
        });

        addFavColor = view.findViewById(R.id.addColor_button);
        noColors = view.findViewById(R.id.no_favourite_colors_textView);
        seeAllFavColors = view.findViewById(R.id.see_all_favourite_colours);
        favouriteColoursRecyclerView = view.findViewById(R.id.favourite_colours_list);
        DatabaseHandler db = new DatabaseHandler(getActivity());
        if(loggedInUser != null) {
            favouriteColors = new ArrayList<>();
            favouriteColors = db.getAllFavouriteColours(loggedInUser, "3");
            Log.d("PROFILE", favouriteColors.size() + " favourite colors for " + loggedInUser.getEmail());
        }

        //linking recyclerView
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean supportsPredictiveItemAnimations() {

                return true;

            }
        };
        myLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        favouriteColoursRecyclerView.setLayoutManager(myLayoutManager);
        favouriteColoursRecyclerView.setAdapter(new FavouriteColorsAdapter(favouriteColors));

        if(favouriteColors.size() > 0){
            favouriteColoursRecyclerView.setVisibility(View.VISIBLE);
            seeAllFavColors.setVisibility(View.VISIBLE);
            noColors.setVisibility(View.GONE);
            addFavColor.setVisibility(View.GONE);
        }else{
            favouriteColoursRecyclerView.setVisibility(View.GONE);
            seeAllFavColors.setVisibility(View.GONE);
            noColors.setVisibility(View.VISIBLE);
            addFavColor.setVisibility(View.VISIBLE);
        }

        addFavColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ColorPickerActivity.class);
                intent.putExtra("USER", loggedInUser);
                startActivity(intent);
            }
        });

        seeAllFavColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_content, FavouriteColorsFragment.newInstance(loggedInUser))
                        .addToBackStack(null)
                        .commit();
            }
        });

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
