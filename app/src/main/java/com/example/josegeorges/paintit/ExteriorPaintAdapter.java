package com.example.josegeorges.paintit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This adapter will be used to display all the exterior paints to the user
 */

public class ExteriorPaintAdapter extends RecyclerView.Adapter<ExteriorPaintAdapter.ExteriorPaintRecyclerViewHolder>{

    protected ArrayList<ExteriorPaint> list;


    public ExteriorPaintAdapter(ArrayList<ExteriorPaint> list) {
        this.list = list;
    }

    @Override
    public ExteriorPaintRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view and inflate with Exterior_paint_item.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exterior_paint_item, parent, false);
        ExteriorPaintRecyclerViewHolder holder = new ExteriorPaintRecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ExteriorPaintRecyclerViewHolder holder, int position) {
        holder.paintImage.setImageResource(list.get(position).getPaintimage());
        holder.paintName.setText(list.get(position).getPaintName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ExteriorPaintRecyclerViewHolder extends RecyclerView.ViewHolder{

        protected ImageView paintImage;
        protected TextView paintName;


        public ExteriorPaintRecyclerViewHolder(View itemView) {
            super(itemView);
            paintImage = itemView.findViewById(R.id.exteriorPaint_imageView);
            paintName  = itemView.findViewById(R.id.exteriorPaint_name);
        }


    }


}
