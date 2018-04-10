package com.example.josegeorges.paintit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import com.example.josegeorges.paintit.POJO.Color;
import com.example.josegeorges.paintit.POJO.Item;
import com.example.josegeorges.paintit.POJO.User;
import com.example.josegeorges.paintit.utils.DatabaseHandler;

import java.util.ArrayList;

import static com.example.josegeorges.paintit.LoginActivity.FIRST_TIME;


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
    FavouriteColorsAdapter favouriteColorsAdapter;

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
            Log.d("USER", loggedInUser.getEmail() + " it's now on its profile");
        }
        DatabaseHandler db = new DatabaseHandler(getActivity());
        if(loggedInUser != null) {
            favouriteColors = new ArrayList<>();
            favouriteColors = db.getAllFavouriteColours(loggedInUser, PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("pref_key_color_display", "3"));
            Log.d("PROFILE", favouriteColors.size() + " favourite colors for " + loggedInUser.getEmail());
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //TODO: THIS METHOD CONSTANTLY CREATES DATA, WE GOTTA FIND A WAY TO MAKE IT UNIQUE
        initializeData();

        swipeRefreshLayout = view.findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //we check the db and add whatever we have now to the adapter list and notify if changes where made
                DatabaseHandler db = new DatabaseHandler(getActivity());
                favouriteColors = db.getAllFavouriteColours(loggedInUser, "3");
                favouriteColorsAdapter.list = db.getAllFavouriteColours(loggedInUser, PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("pref_key_color_display", "3"));
                favouriteColorsAdapter.notifyDataSetChanged();

                //checking again if there is any records to change the visibility of things
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
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        addFavColor = view.findViewById(R.id.addColor_button);
        noColors = view.findViewById(R.id.no_favourite_colors_textView);
        seeAllFavColors = view.findViewById(R.id.see_all_favourite_colours);
        favouriteColoursRecyclerView = view.findViewById(R.id.favourite_colours_list);


        //linking recyclerView
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean supportsPredictiveItemAnimations() {

                return true;

            }
        };
        myLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        favouriteColoursRecyclerView.setLayoutManager(myLayoutManager);
        favouriteColorsAdapter = new FavouriteColorsAdapter(favouriteColors);
        favouriteColoursRecyclerView.setAdapter(favouriteColorsAdapter);

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


    /**
     * This method initializes the TYPE and ITEM table
     */
    public void initializeData(){
        //get the shared pref
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
            DatabaseHandler db = new DatabaseHandler(getContext());
            db.addType("Interior Paints");
            db.addType("Exterior Paints");
            db.addType("Stains");
            db.addType("Brushes");


            //Add the interior paints to the database
            db.addItem(new Item(422987391, 22, "Acrylic Flat", R.drawable.ic_edit_black_24dp, 10, 0));
            db.addItem(new Item(422987392, 22, "Acrylic Eggshell", R.drawable.ic_edit_black_24dp, 10, 0));
            db.addItem(new Item(422987393, 22, "Acrylic Satin", R.drawable.ic_edit_black_24dp, 10, 0));
            db.addItem(new Item(422987394, 22, "Acrylic Semi-Gloss", R.drawable.ic_edit_black_24dp, 10, 0));
            db.addItem(new Item(422787395, 22, "Acrylic Gloss", R.drawable.ic_edit_black_24dp, 10, 0));
            db.addItem(new Item(422987396, 22, "Alkyed Flat", R.drawable.ic_edit_black_24dp, 10, 0));
            db.addItem(new Item(422987397, 22, "Alkyed Semi-Gloss", R.drawable.ic_edit_black_24dp, 10, 0));
            db.addItem(new Item(422987398, 22, "Alkyed Gloss", R.drawable.ic_edit_black_24dp, 10, 0));

            //Add the exterior paints to the database
            db.addItem(new Item(322987391, 22, "Exterior Flat", R.drawable.ic_edit_black_24dp, 10, 1));
            db.addItem(new Item(322987392, 22, "Exterior Satin Enamel", R.drawable.ic_edit_black_24dp, 10, 1));
            db.addItem(new Item(322987393, 22, "Exterior Gloss Enamel", R.drawable.ic_edit_black_24dp, 10, 1));
            db.addItem(new Item(322987394, 22, "Exterior Semi-Gloss Enamel", R.drawable.ic_edit_black_24dp, 10, 1));
            db.addItem(new Item(322987395, 22, "Exterior Matte", R.drawable.ic_edit_black_24dp, 10, 1));

            //Add the stains to the database
            db.addItem(new Item(222987391, 22, "Hardwood Finish", R.drawable.ic_edit_black_24dp, 10, 2));
            db.addItem(new Item(222987392, 22, "Clearwood Finish", R.drawable.ic_edit_black_24dp, 10, 2));
            db.addItem(new Item(222987393, 22, "Semi-Transparent Oil Finish", R.drawable.ic_edit_black_24dp, 10, 2));
            db.addItem(new Item(222987394, 22, "Semi-Transparent Deck Stain", R.drawable.ic_edit_black_24dp, 10, 2));
            db.addItem(new Item(222987395, 22, "Solid Deck Stain", R.drawable.ic_edit_black_24dp, 10, 2));
            db.addItem(new Item(222987396, 22, "All Purpose Deck Wash", R.drawable.ic_edit_black_24dp, 10, 2));
            db.addItem(new Item(222987397, 22, "Stain Remover", R.drawable.ic_edit_black_24dp, 10, 2));

            //Add the brushes the database
            db.addItem(new Item(122987391, 22, "Round Brush", R.drawable.ic_edit_black_24dp, 10, 3));
            db.addItem(new Item(122987392, 22, "Flat Brush", R.drawable.ic_edit_black_24dp, 10, 3));
            db.addItem(new Item(122987393, 22, "Angular Flat Brush", R.drawable.ic_edit_black_24dp, 10, 3));
            db.addItem(new Item(122987394, 22, "Bright Brush", R.drawable.ic_edit_black_24dp, 10, 3));
            db.addItem(new Item(122987395, 22, "Filbert Brush", R.drawable.ic_edit_black_24dp, 10, 3));
            db.addItem(new Item(122987396, 22, "Fan Brush", R.drawable.ic_edit_black_24dp, 10, 3));
            db.addItem(new Item(122987397, 22, "Roller", R.drawable.ic_edit_black_24dp, 10, 3));


            //Close the database
            db.close();

//        Log.d("ITEMS", "should have been populated");
//
//        sharedPref.edit().putBoolean(FIRST_TIME, false).apply();
    }
}
