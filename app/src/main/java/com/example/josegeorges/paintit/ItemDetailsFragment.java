package com.example.josegeorges.paintit;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.josegeorges.paintit.POJO.Color;
import com.example.josegeorges.paintit.POJO.Item;
import com.example.josegeorges.paintit.POJO.ShoppingCartList;
import com.example.josegeorges.paintit.POJO.User;
import com.example.josegeorges.paintit.utils.DatabaseHandler;

import java.util.ArrayList;

/**
 * Created by josegeorges on 2018-04-10.
 */

public class ItemDetailsFragment extends Fragment{

    //Main Layout
    CoordinatorLayout myCoordinatorLayout;

    //item being passed
    public static final String ITEM_PASSED = "item_passed";
    Item item;

    //name of the item
    TextView itemTitle;

    //price of the item
    TextView itemPrice;
    TextView itemIndividualPrice;

    //Spinners to show options available
    Spinner itemSizeSpinner;
    Spinner itemQuantitySpinner;
    Spinner itemColorSpinner;

    //image of the item
    ImageView itemImage;

    //Shopping Cart List
    ShoppingCartList shoppingCartList;

    //values of spinners
    int quantity = 1; //default quantity is 1
    String sizePrice = "20"; //default price is 20
    String size = "3"; //default size is 3

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

        //the shopping cart
        shoppingCartList = ShoppingCartList.getIntance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);

        //Link The Views
        myCoordinatorLayout = getActivity().findViewById(R.id.myCoordinatorLayout);
        itemTitle = view.findViewById(R.id.item_title);
        itemImage = view.findViewById(R.id.item_image);
        itemPrice = view.findViewById(R.id.item_price);
        itemIndividualPrice = view.findViewById(R.id.item_individual_price);
        itemSizeSpinner = view.findViewById(R.id.item_size);
        itemColorSpinner = view.findViewById(R.id.item_color);
        itemQuantitySpinner = view.findViewById(R.id.item_quantity);
        cancel = view.findViewById(R.id.cancel_button);
        addToCart = view.findViewById(R.id.next_button);

        if(item != null) {
            //add the information from the item
            itemTitle.setText(item.getDescription());
            itemPrice.setText(String.valueOf(item.getPrice()));
            itemIndividualPrice.setText(String.valueOf(item.getPrice()));
            itemImage.setImageResource(item.getImageView());
        }

        //getting the sizes and colors from the db
        DatabaseHandler db = new DatabaseHandler(getContext());
        ArrayList<String> availableSizes = db.getSizes(String.valueOf(item.getItemTypeId()), item.getDescription());
        ArrayList<Color> availableColors = db.getAllFavouriteColours(getLoggedInUser(), null);
        //making the quantities array. It will go up to 5 so that's the limit per customer
        ArrayList<Integer> quantities = new ArrayList<>();
        for(int i = 0; i < 5; i ++){
            quantities.add(i+1);
        }

        //setting up the sizes spinner

        final ArrayAdapter<String> sizesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, availableSizes);
        itemSizeSpinner.setAdapter(sizesAdapter);
        //Change the price of the item depending on the size they select
        itemSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                DatabaseHandler db = new DatabaseHandler(getContext());
                size = (String) parent.getItemAtPosition(i);
                sizePrice = db.getPrice(String.valueOf(item.getItemTypeId()), item.getDescription(), size);
                itemPrice.setText("$"+String.valueOf(Double.parseDouble(sizePrice) * quantity));
                itemIndividualPrice.setText("($"+sizePrice+")");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //setting up the colors spinner

        ArrayAdapter<Color> colorsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, availableColors);
        itemColorSpinner.setAdapter(colorsAdapter);

        //setting up the quantities spinner

        ArrayAdapter<Integer> quantitiesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, quantities);
        itemQuantitySpinner.setAdapter(quantitiesAdapter);
        //Change the price of the item depending on the quantity they select
        itemQuantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                quantity = (Integer) parent.getItemAtPosition(i);
                itemPrice.setText("$"+String.valueOf(Double.parseDouble(sizePrice) * quantity));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString(LoginActivity.USER_EMAIL, "").equals(LoginFragment.GUEST_EMAIL)){
                    //don't show shopping cart to the guest user
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Access Denied")
                            .setMessage("This portion of the app is accessible only for logged in users!")
                            .show();
                }else {
                    //show shopping cart if it's not the guest user
                    Item tempItem = new Item(item.getItemID(), item.getUpc(), Double.parseDouble(sizePrice) * quantity, item.getItemTypeId(), Integer.parseInt(size), item.getDescription());
                    if(shoppingCartList != null) {
                        shoppingCartList.getList().add(tempItem);
                        Snackbar mySnackbar = Snackbar.make(myCoordinatorLayout,
                                R.string.item_added_cart, Snackbar.LENGTH_SHORT);
                        mySnackbar.setAction(R.string.checkout_string, new MyCheckoutListener());
                        mySnackbar.show();
                    }
                }
            }
        });


        db.close();
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

    public class MyCheckoutListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content, new ShoppingCartFragment()).commit();
        }
    }



}
