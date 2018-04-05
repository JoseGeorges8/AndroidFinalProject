package com.example.josegeorges.paintit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This adapter will be used to display all the paint brushes to the user
 */

public class BrushesAdapter extends RecyclerView.Adapter<BrushesAdapter.BrushesRecyclerViewHolder>{

    protected ArrayList<Brushes> list;


    public BrushesAdapter(ArrayList<Brushes> list) {
        this.list = list;
    }

    @Override
    public BrushesRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view and inflate with brushes_item.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brushes_item, parent, false);
        BrushesRecyclerViewHolder holder = new BrushesRecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BrushesRecyclerViewHolder holder, int position) {
        holder.brushImage.setImageResource(list.get(position).getBrushImageView());
        holder.brushName.setText(list.get(position).getBrushName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class BrushesRecyclerViewHolder extends RecyclerView.ViewHolder{

        protected ImageView brushImage;
        protected TextView brushName;


        public BrushesRecyclerViewHolder(View itemView) {
            super(itemView);
            brushImage = itemView.findViewById(R.id.brushes_imageView);
            brushName  = itemView.findViewById(R.id.brushes_name);
        }


    }


}
