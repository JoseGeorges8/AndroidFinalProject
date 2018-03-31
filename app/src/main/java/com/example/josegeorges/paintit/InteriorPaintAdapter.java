package com.example.josegeorges.paintit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This adapter will be used to display all the interior paints to the user
 */

    public class InteriorPaintAdapter extends RecyclerView.Adapter<InteriorPaintAdapter.InteriorPaintRecyclerViewHolder>{

    protected ArrayList<InteriorPaint> list;


    public InteriorPaintAdapter(ArrayList<InteriorPaint> list) {
        this.list = list;
    }

    @Override
    public InteriorPaintRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view and inflate with interior_paint_item.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interior_paint_item, parent, false);
        InteriorPaintRecyclerViewHolder holder = new InteriorPaintRecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(InteriorPaintRecyclerViewHolder holder, int position) {
        holder.paintImage.setImageResource(list.get(position).getPaintmage());
        holder.paintName.setText(list.get(position).getPaintName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class InteriorPaintRecyclerViewHolder extends RecyclerView.ViewHolder{

        protected ImageView paintImage;
        protected TextView paintName;


        public InteriorPaintRecyclerViewHolder(View itemView) {
            super(itemView);
            paintImage = itemView.findViewById(R.id.paintCanImageView);
            paintName  = itemView.findViewById(R.id.paintName);
        }


    }


}
