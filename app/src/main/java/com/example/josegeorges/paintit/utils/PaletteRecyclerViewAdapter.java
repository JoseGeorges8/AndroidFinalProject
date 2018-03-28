package com.example.josegeorges.paintit.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josegeorges.paintit.MainActivity;
import com.example.josegeorges.paintit.PalletePickerActivity;
import com.example.josegeorges.paintit.R;
import com.example.josegeorges.paintit.RGBFragment;

import java.util.ArrayList;

/**
 * Created by josegeorges on 2018-03-23.
 */

public class PaletteRecyclerViewAdapter extends RecyclerView.Adapter<PaletteRecyclerViewAdapter.RecyclerViewHolder>{

    private ArrayList<String> list;
    Context context;

    private FrameLayout frame;

    public PaletteRecyclerViewAdapter() {
        list = new ArrayList<>();
        list.add("a");
        list.add("v");
        list.add("b");
        list.add("c");
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_palette_color, parent, false);
        final RecyclerViewHolder holder = new RecyclerViewHolder(view);
        context = parent.getContext();
        this.frame = holder.frame;

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        final String index = list.get(position);
        final int location = holder.getAdapterPosition();

        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(location);
                Log.d("APPNAME", location + " removed");
                notifyDataSetChanged();
            }
        });

        holder.frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("APPNAME", location + " selected");
            }
        });

        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((PalletePickerActivity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int devicewidth = displaymetrics.widthPixels / list.size();
        holder.frame.getLayoutParams().width = devicewidth;
        holder.frame.setBackgroundColor(Color.rgb(0, 0, 0));
        holder.frame.refreshDrawableState();

        Log.d("APPNAME", "" + devicewidth);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        protected ImageView deleteIcon;
        protected FrameLayout frame;


        public RecyclerViewHolder(View itemView) {
            super(itemView);

            deleteIcon = itemView.findViewById(R.id.palette_color_delete_icon);
            frame = itemView.findViewById(R.id.palette_color_frame);

        }


    }

}
