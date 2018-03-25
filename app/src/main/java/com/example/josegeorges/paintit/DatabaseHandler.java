package com.example.josegeorges.paintit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
            COLUMN_ORDERNUMBER + " TEXT," + COLUMN_DATEORDERED + " TEXT,"
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

    /**
     * Create Operations
     */

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, user.getFirstName());
        values.put(COLUMN_LASTNAME, user.getLastName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_RECOVERYEMAIL, user.getRecoveryEmail());
        values.put(COLUMN_PHONENUMBER, user.getPhoneNumber());
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public void addOrder(Order order){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDERID, order.getOrderID());
        values.put(COLUMN_ORDERNUMBER, order.getOrderNumber());
        values.put(COLUMN_DATEORDERED, order.getDateOrdered());
        values.put(COLUMN_USERID, order.getUserID());
        db.insert(TABLE_ORDERS, null, values);
        db.close();
    }

    public void addItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_UPC, item.getUpc());
        values.put(COLUMN_DESCRIPTION, item.getDescription());
        values.put(COLUMN_PRICE, item.getPrice());
        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }

    public void addColor(Color color){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_HEXVALUE, color.getHexValue());
        values.put(COLUMN_COLORNAME, color.getColorName());
        values.put(COLUMN_TIMESTAMP, color.getTimestamp());
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    /**
     * READ OPERATIONS
     */

    // Users
    public User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;
        //table name, String Array of column names, query, String array of values that will
        // be inserted into the query
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_USERID, COLUMN_FIRSTNAME, COLUMN_LASTNAME, COLUMN_EMAIL,
                        COLUMN_RECOVERYEMAIL, COLUMN_PHONENUMBER},
                COLUMN_USERID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            user = new User(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5));
        }
        db.close();
        return user;
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> userList = new ArrayList<User>();
        String query = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                userList.add(new User(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)));
            } while (cursor.moveToNext());
        }

        db.close();
        return userList;
    }

    // Orders
    public Order getOrder(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Order order = null;
        //table name, String Array of column names, query, String array of values that will
        // be inserted into the query
        Cursor cursor = db.query(TABLE_ORDERS,
                new String[]{COLUMN_ORDERID, COLUMN_ORDERNUMBER, COLUMN_DATEORDERED, COLUMN_USERID},
                COLUMN_ORDERID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            order = new Order(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    Integer.parseInt(cursor.getString(3)));
        }
        db.close();
        return order;
    }

    public ArrayList<Order> getAllOrders(){
        ArrayList<Order> orderList = new ArrayList<Order>();
        String query = "SELECT * FROM " + TABLE_ORDERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do {
                orderList.add(new Order(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3))));
            } while (cursor.moveToNext());
        }
        db.close();
        return orderList;
    }

    // Items
    public Item getItem(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Item item = null;
        //table name, String Array of column names, query, String array of values that will
        // be inserted into the query
        Cursor cursor = db.query(TABLE_ITEMS,
                new String[]{COLUMN_ITEMID, COLUMN_UPC, COLUMN_PRICE, COLUMN_PRICE},
                COLUMN_ITEMID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            item = new Item(Integer.parseInt(cursor.getString(0)),
                    Integer.parseInt(cursor.getString(1)),
                    Double.parseDouble(cursor.getString(2)),
                    cursor.getString(1));
        }
        db.close();
        return item;
    }

    public ArrayList<Item> getAllItems(){
        ArrayList<Item> itemList = new ArrayList<Item>();
        String query = "SELECT * FROM " + TABLE_ITEMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                itemList.add(new Item(Integer.parseInt(cursor.getString(0)),
                        Integer.parseInt(cursor.getString(1)),
                        Double.parseDouble(cursor.getString(2)),
                        cursor.getString(1));
            } while (cursor.moveToNext());
        }
        db.close();
        return itemList;
    }

    // Colors
    public Color getColor(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Color color = null;
        //table name, String Array of column names, query, String array of values that will
        // be inserted into the query
        Cursor cursor = db.query(TABLE_COLORS,
                new String[]{COLUMN_HEXVALUE, COLUMN_COLORNAME, COLUMN_TIMESTAMP},
                COLUMN_HEXVALUE + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            color = new Color(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2));
        }
        db.close();
        return color;
    }

    public ArrayList<Color> getAllColors(){
        ArrayList<Color> colorList = new ArrayList<Color>();
        String query = "SELECT * FROM " + TABLE_COLORS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                colorList.add(new Color(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2)));
            } while (cursor.moveToNext());
        }

        db.close();
        return colorList;



}
