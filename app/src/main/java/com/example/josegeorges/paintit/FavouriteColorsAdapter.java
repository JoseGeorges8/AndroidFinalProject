package com.example.josegeorges.paintit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by josegeorges on 2018-03-28.
 */

/**
 * This adapter will serve to display the favourite colors related to the user
 */
public class FavouriteColorsAdapter extends RecyclerView.Adapter<FavouriteColorsAdapter.FavouriteRecyclerViewHolder>{

    protected ArrayList<Color> list;
    private Context context;

    public FavouriteColorsAdapter(ArrayList<Color> list) {
        this.list = list;
    }

    @Override
    public FavouriteRecyclerViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_colours_items, parent, false);
        final FavouriteRecyclerViewHolder holder = new FavouriteRecyclerViewHolder(view);
        context = parent.getContext();


        //deleting a color from the list
        holder.deleteColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(parent.getContext())
                        .setTitle("Delete Color")
                        .setMessage("Are you sure you want to delete this color?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //get the selected item
                                int color = holder.getAdapterPosition();
                                //get db
                                DatabaseHandler db = new DatabaseHandler(parent.getContext());
                                //delete from the db
                                db.deleteColor(list.get(color));
                                //delete from the list
                                list.remove(color);
                                //notify recycler view
                                notifyItemRemoved(color);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        //editing a color from the list
        holder.editColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(FavouriteRecyclerViewHolder holder, int position) {
        holder.colorValue.setBackgroundColor(list.get(position).getHexValue());
        holder.colorName.setText(list.get(position).getColorName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class FavouriteRecyclerViewHolder extends RecyclerView.ViewHolder{

        protected ImageView colorValue;
        protected TextView colorName;
        protected ImageButton editColor;
        protected ImageButton deleteColor;


        public FavouriteRecyclerViewHolder(View itemView) {
            super(itemView);
            colorValue = itemView.findViewById(R.id.color_imageView);
            colorName  = itemView.findViewById(R.id.favourite_color_name_textView);
            editColor  = itemView.findViewById(R.id.favourite_color_edit);
            deleteColor  = itemView.findViewById(R.id.favourite_color_delete);
        }


    }

}

