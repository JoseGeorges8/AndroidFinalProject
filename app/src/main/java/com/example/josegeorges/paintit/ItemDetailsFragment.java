package com.example.josegeorges.paintit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.josegeorges.paintit.POJO.Color;
import com.example.josegeorges.paintit.POJO.Item;
import com.example.josegeorges.paintit.POJO.User;
import com.example.josegeorges.paintit.adapters.ItemsAdapter;
import com.example.josegeorges.paintit.utils.DatabaseHandler;

import java.util.ArrayList;

/**
 * Created by josegeorges on 2018-04-10.
 */

public class ItemDetailsFragment extends Fragment{

    //item being passed
    public static final String ITEM_PASSED = "item_passed";
    Item item;

    //name of the item
    TextView itemTitle;

    //price of the item
    TextView itemPrice;

    //Spinners to show options available
    Spinner itemSizeSpinner;
    Spinner itemQuantitySpinner;
    Spinner itemColorSpinner;

    //image of the item
    ImageView itemImage;

    //buttons to cancel and add to cart
    Button cancel;
    Button addToCart;

    //default constructor
    public ItemDetailsFragment(){

    }

    public static ItemDetailsFragment newInstance(Item item) {
        ItemDetailsFragment fragment = new ItemDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ITEM_PASSED, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            item = getArguments().getParcelable(ITEM_PASSED);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);

        //Link The Views
        itemTitle = view.findViewById(R.id.item_title);
        itemImage = view.findViewById(R.id.item_image);
        itemPrice = view.findViewById(R.id.item_price);
        itemSizeSpinner = view.findViewById(R.id.item_size);
        itemColorSpinner = view.findViewById(R.id.item_color);
        itemQuantitySpinner = view.findViewById(R.id.item_quantity);
        cancel = view.findViewById(R.id.cancel_button);
        addToCart = view.findViewById(R.id.next_button);

        //getting the sizes and colors from the db
        DatabaseHandler db = new DatabaseHandler(getContext());
        ArrayList<String> availableSizes = db.getSizes();
        ArrayList<Color> availableColors = db.getAllFavouriteColours(getLoggedInUser(), null);
        //making the quantities array. It will go up to 5 so that's the limit per customer
        ArrayList<Integer> quantities = new ArrayList<>();
        for(int i = 0; i < 5; i ++){
            quantities.add(i+1);
        }

        //setting up the sizes spinner
        ArrayAdapter<String> sizesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, availableSizes);
        itemSizeSpinner.setAdapter(sizesAdapter);

        //setting up the colors spinner
        ArrayAdapter<Color> colorsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, availableColors);
        itemColorSpinner.setAdapter(colorsAdapter);

        //setting up the quantities spinner
        ArrayAdapter<Integer> quantitiesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, quantities);
        itemQuantitySpinner.setAdapter(quantitiesAdapter);


        if(item != null) {
            //add the information from the item
            itemTitle.setText(item.getDescription());
            itemPrice.setText(String.valueOf(item.getPrice()));
            itemImage.setImageResource(item.getImageView());
        }


        return view;
    }

    public User getLoggedInUser(){
        //get the shared pref
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

        int id = sharedPref.getInt(LoginActivity.USER_ID, 0);
        String email = sharedPref.getString(LoginActivity.USER_EMAIL, "");
        String fname = sharedPref.getString(LoginActivity.USER_FNAME, "");
        String lname = sharedPref.getString(LoginActivity.USER_LNAME, "");
        String password = sharedPref.getString(LoginActivity.USER_PASSWORD, "");
        String emailRecovery = sharedPref.getString(LoginActivity.USER_RECOVERY_EMAIL, "");
        String phone = sharedPref.getString(LoginActivity.USER_PHONE, "");

        //create the user
        User isUser = new User(id, fname, lname, email, password, emailRecovery, phone);

        return isUser;
    }


}
