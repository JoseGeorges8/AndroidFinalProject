package com.example.josegeorges.paintit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Keegan on 2018-03-24.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database version
    public static final int DATABASE_VERSION = 1;

    // name the database
    public static final String DATABASE_NAME = "paintit";

    /**
     * Create names for each of the tables
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

    /**
     * Create column names for each of the tables
     *
     */

    // Users Table
    public static final String COLUMN_USERID = "user_id";
    public static final String COLUMN_FIRSTNAME = "first_name";
    public static final String COLUMN_LASTNAME = "last_name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_RECOVERYEMAIL = "recovery_email";
    public static final String COLUMN_PHONENUMBER = "phone_number";

    // Orders Table
    public static final String COLUMN_ORDERID = "order_id";
    public static final String COLUMN_ORDERNUMBER = "order_number";
    public static final String COLUMN_DATEORDERED = "date_ordered";
    // Uses USERID

    // Items Table
    public static final String COLUMN_ITEMID = "item_id";
    public static final String COLUMN_UPC = "upc";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_DESCRIPTION = "description";

    // OrderItems Table **** Linking Table ****
    public static final String COLUMN_ORDERQUANTITY = "order_quantity";
    // Uses ORDERID
    // Uses ITEMID

    // Palettes Table
    public static final String COLUMN_PALETTEID = "palette_id";
    public static final String COLUMN_PALETTENAME = "palette_name";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    // Uses USERID

    // Colors Table
    public static final String COLUMN_HEXVALUE = "hex_value";
    public static final String COLUMN_COLORNAME = "color_name";
    // Uses TIMESTAMP

    /**
     * PaletteColors Table **** Linking Table ****
     * Uses PALETTEID and COLORID
      */

    /**
     * FavoriteColors Table
     * Uses USERID
     * Uses COLORID
     */

    /**
     * FavoritePalettes Table
     * Uses USERID
     * Uses PALETTEID
     */
    


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
