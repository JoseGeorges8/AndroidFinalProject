package com.example.josegeorges.paintit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.josegeorges.paintit.POJO.Color;
import com.example.josegeorges.paintit.POJO.Order;
import com.example.josegeorges.paintit.POJO.User;
import com.example.josegeorges.paintit.utils.DatabaseHandler;

import java.util.ArrayList;


public class OrdersFragment extends Fragment {

    public OrdersFragment() {
        // Required empty public constructor
    }

    //for the bundle
    private static final String ARG_PARAM1 = "param1";

    //setting up toolbar
    private Toolbar toolbar;

    //setting up recyclerView
    private RecyclerView recyclerView;
    ArrayList<Order> orders;
    User loggedInUser;

    public static OrdersFragment newInstance(User user) {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);if (getArguments() != null) {
            loggedInUser = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_orders, container, false);

        DatabaseHandler db = new DatabaseHandler(getActivity());
        if(loggedInUser != null) {
            orders = db.getAllOrders(loggedInUser.getUserID());
        }
        db.close();


        //setting up an action bar
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        setHasOptionsMenu(true);

        //linking recyclerView
        recyclerView = view.findViewById(R.id.recentOrders_recyclerView);
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean supportsPredictiveItemAnimations() {

                return true;
            }
        };
        myLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setAdapter(new RecentOrdersAdapter(orders));


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    //this method takes care of changing the toolbar style
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar_noicons, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


}
