package com.example.josegeorges.paintit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Keegan on 2018-03-24.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    /**
     * Keep track of the database version
     */
    public static final int DATABASE_VERSION = 1;

    /**
     * Create the name of the database
     */
    public static final String DATABASE_NAME = "hikingclub";

    /**
     * Create the names of all the tables
     */
    public static final String TABLE_USERS = "user";
    public static final String TABLE_ORDERS = "order";
    public static final String TABLE_ITEMS = "item";
    public static final String TABLE_ITEMORDERS = "item_order";
    public static final String TABLE_COLORS = "colors";
    public static final String TABLE_PALETTES = "palette";
    public static final String TABLE_PALETTECOLORS = "palette_color";
    public static final String TABLE_FAVORITECOLORS = "favorite_color";
    public static final String TABLE_FAVORITEPALETTES = "favorite_palette";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
