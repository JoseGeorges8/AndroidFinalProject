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

    /**
     * Create Table Statements
     */

    // Users Table
    public static final String CREATE_USERS_TABLE = "CREATE TABLE " +
            TABLE_USERS + "(" + COLUMN_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_FIRSTNAME + " VARCHAR(25)," + COLUMN_LASTNAME + " VARCHAR(25),"
            + COLUMN_EMAIL + " TEXT," + COLUMN_PASSWORD + " TEXT,"
            + COLUMN_RECOVERYEMAIL + " TEXT," + COLUMN_PHONENUMBER + " CHAR(10))";

    // Orders Table
    public static final String CREATE_ORDERS_TABLE = "CREATE TABLE " +
            TABLE_ORDERS + "(" + COLUMN_ORDERID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_ORDERNUMBER + " INTEGER," + COLUMN_DATEORDERED + " TEXT,"
            + COLUMN_USERID +
            " INTEGER REFERENCES " + TABLE_USERS + "(" + COLUMN_USERID
            + "))";

    // Items Table
    public static final String CREATE_ITEMS_TABLE = "CREATE TABLE " +
            TABLE_ITEMS + "(" + COLUMN_ITEMID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_UPC + " INTEGER," + COLUMN_PRICE + " DECIMAL,"
            + COLUMN_DESCRIPTION + " TEXT)";

    // OrderItems Table
    public static final String CREATE_ITEMORDERS_TABLE = "CREATE TABLE " +
            TABLE_ITEMORDERS + "(" + COLUMN_ORDERID + " INTEGER REFERENCES " +
            TABLE_ORDERS + "(" + COLUMN_ORDERID + ")," + COLUMN_ITEMID +
            " INTEGER REFERENCES " + TABLE_ITEMS + "(" + COLUMN_ITEMID + "),"
            + COLUMN_LASTNAME + " INTEGER)";

    // Palettes Table
    public static final String CREATE_PALETTES_TABLE = "CREATE TABLE " +
            TABLE_PALETTES + "(" + COLUMN_PALETTEID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_PALETTENAME + " TEXT," + COLUMN_TIMESTAMP + " TEXT,"
            + COLUMN_USERID +
            " INTEGER REFERENCES " + TABLE_USERS + "(" + COLUMN_USERID
            + "))";

    // Colors Table
    public static final String CREATE_COLORS_TABLE = "CREATE TABLE " +
            TABLE_COLORS + "(" + COLUMN_HEXVALUE + " INTEGER PRIMARY KEY,"
            + COLUMN_COLORNAME + " TEXT," + COLUMN_TIMESTAMP + " TEXT,)";

    // PaletteColors Table
    public static final String CREATE_PALETTECOLORS_TABLE = "CREATE TABLE " +
            TABLE_PALETTECOLORS + "(" + COLUMN_PALETTEID + " INTEGER REFERENCES " +
            TABLE_PALETTES + "(" + COLUMN_PALETTEID + ")," + COLUMN_HEXVALUE +
            " INTEGER REFERENCES " + TABLE_COLORS + "(" + COLUMN_HEXVALUE + "))";

    // FavoriteColors Table
    public static final String CREATE_FAVORITECOLORS_TABLE = "CREATE TABLE " +
            TABLE_FAVORITECOLORS + "(" + COLUMN_USERID + " INTEGER REFERENCES " +
            TABLE_USERS + "(" + COLUMN_USERID + ")," + COLUMN_HEXVALUE +
            " INTEGER REFERENCES " + TABLE_COLORS + "(" + COLUMN_HEXVALUE + "))";

    // FavoritePalettes Table
    public static final String CREATE_FAVORITEPALETTES_TABLE = "CREATE TABLE " +
            TABLE_FAVORITEPALETTES + "(" + COLUMN_PALETTEID + " INTEGER REFERENCES " +
            TABLE_PALETTES + "(" + COLUMN_PALETTEID + ")," + COLUMN_USERID +
            " INTEGER REFERENCES " + TABLE_USERS + "(" + COLUMN_USERID + "))";






    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_ORDERS_TABLE);
        db.execSQL(CREATE_ITEMS_TABLE);
        db.execSQL(CREATE_ITEMORDERS_TABLE);
        db.execSQL(CREATE_COLORS_TABLE);
        db.execSQL(CREATE_PALETTES_TABLE);
        db.execSQL(CREATE_PALETTECOLORS_TABLE);
        db.execSQL(CREATE_FAVORITECOLORS_TABLE);
        db.execSQL(CREATE_FAVORITEPALETTES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COLORS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PALETTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PALETTECOLORS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITEPALETTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITECOLORS);

    }
}
