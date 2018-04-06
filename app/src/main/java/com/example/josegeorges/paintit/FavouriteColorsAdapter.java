package com.example.josegeorges.paintit;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.josegeorges.paintit.POJO.Color;
import com.example.josegeorges.paintit.utils.DatabaseHandler;


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

        holder.colorName.getBackground().clearColorFilter();

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
                Drawable editDrawable = holder.editColor.getDrawable();

                if(editDrawable.getConstantState().equals(context.getResources().getDrawable(R.drawable.ic_edit_black_24dp).getConstantState())){
                    holder.colorName.setKeyListener((KeyListener) holder.colorName.getTag());
                    holder.colorName.getBackground().clearColorFilter();

                    holder.editColor.requestFocus();
                    holder.editColor.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_check_black_24dp));
                }else{
                    holder.colorName.setTag(holder.colorName.getKeyListener());
                    holder.colorName.setKeyListener(null);
                    holder.colorName.getBackground().setColorFilter(context.getResources().getColor(android.R.color.transparent), PorterDuff.Mode.SRC_IN);

                    DatabaseHandler db = new DatabaseHandler(context);
                    int result = db.updateColor(list.get(holder.getAdapterPosition()), holder.colorName.getText().toString());
                    notifyItemChanged(holder.getAdapterPosition());
                    if(result != -1)
                        Toast.makeText(context, "Color name successfuly updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(context, "Coult not update color name", Toast.LENGTH_SHORT).show();
                    holder.editColor.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_edit_black_24dp));
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(FavouriteRecyclerViewHolder holder, int position) {
        holder.colorValue.setBackgroundColor(list.get(position).getHexValue());
        holder.colorName.setText(list.get(position).getColorName());
        holder.colorName.setTag(holder.colorName.getKeyListener());
        holder.colorName.setKeyListener(null);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class FavouriteRecyclerViewHolder extends RecyclerView.ViewHolder{

        protected ImageView colorValue;
        protected EditText colorName;
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

