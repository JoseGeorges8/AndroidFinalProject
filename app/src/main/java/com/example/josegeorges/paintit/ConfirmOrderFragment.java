package com.example.josegeorges.paintit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.josegeorges.paintit.POJO.Order;

public class ConfirmOrderFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Order done
    Order order;

    //views
    TextView name;
    TextView message;
    Button done;

    public ConfirmOrderFragment() {
        // Required empty public constructor
    }

    public static ConfirmOrderFragment newInstance(Order order) {
        ConfirmOrderFragment fragment = new ConfirmOrderFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, order);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            order = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm_order, container, false);

        name = view.findViewById(R.id.name);
        message = view.findViewById(R.id.message);
        done = view.findViewById(R.id.confirmOrder_button);

        name.setText("Order: " + order.getOrderNumber());

        message.setText("Thank you for shopping with us. Your order has been processed. " +
                "It can be picked on " + order.getPickUpDate() +
                "Please bring valid ID and a recognized form of payment. ");

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
                getActivity().getSupportFragmentManager().popBackStack();
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

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

}
