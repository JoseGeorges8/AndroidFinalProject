package com.example.josegeorges.paintit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class AboutUsFragment extends Fragment {



    public AboutUsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        LinearLayout callUs = view.findViewById(R.id.phone_container);
        callUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "5192222222"));
                if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                    getActivity().startActivity(intent);
                }
            }
        });

        LinearLayout emailUs = view.findViewById(R.id.email_container);
        emailUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] emailAddress = {"customerservice@paintit.ca"};
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
                if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                    getActivity().startActivity(intent);
                }
            }
        });

        LinearLayout likeUs = view.findViewById(R.id.social_media_container);
        likeUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/Trivel-341889966290364/"));
                if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                    getActivity().startActivity(intent);
                }
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
