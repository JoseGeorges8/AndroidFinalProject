package com.example.josegeorges.paintit.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.josegeorges.paintit.POJO.Color;
import com.example.josegeorges.paintit.POJO.Item;
import com.example.josegeorges.paintit.POJO.Order;
import com.example.josegeorges.paintit.POJO.Palette;
import com.example.josegeorges.paintit.POJO.User;

import java.util.ArrayList;

/**
 * Created by Keegan on 2018-03-24.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database version
    public static final int DATABASE_VERSION = 3;

    // name the database
    public static final String DATABASE_NAME = "paintit";

    /**
     * Create names for each of the tables
     */

    public static final String TABLE_USERS = "user";
    public static final String TABLE_ORDERS = "orders";
    public static final String TABLE_ITEMS = "item";
    public static final String TABLE_ITEMORDERS = "item_order";
    public static final String TABLE_COLORS = "colors";
    public static final String TABLE_PALETTES = "palette";
    public static final String TABLE_TYPES = "types";
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
    public static final String COLUMN_SIZE = "size";
    private static final String COLUMN_COLORID = "color_hex";

    // Uses ITEMTYPE

    // OrderItems Table **** Linking Table ****
    public static final String COLUMN_ORDERQUANTITY = "order_quantity";
    // Uses ORDERID
    // Uses ITEMID

    // Palettes Table
    public static final String COLUMN_PALETTEID = "palette_id";
    public static final String COLUMN_PALETTENAME = "palette_name";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    // Uses USERID

    // Types table
    public static final String COLUMN_TYPEID = "type_id";
    public static final String COLUMN_ITEMTYPE = "item_type";

    // Colors Table
    public static final String COLUMN_HEXVALUE = "hex_value";
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
    public static final String COLUMN_COLORNAME = "color_name";

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
            TABLE_USERS + "(" + COLUMN_USERID + " INTEGER PRIMARY KEY,"
            + COLUMN_FIRSTNAME + " VARCHAR(25)," + COLUMN_LASTNAME + " VARCHAR(25),"
            + COLUMN_EMAIL + " TEXT," + COLUMN_PASSWORD + " TEXT,"
            + COLUMN_RECOVERYEMAIL + " TEXT," + COLUMN_PHONENUMBER + " CHAR(10))";

    // Orders Table
    public static final String CREATE_ORDERS_TABLE = "CREATE TABLE " +
            TABLE_ORDERS + "(" + COLUMN_ORDERID + " INTEGER PRIMARY KEY," +
            COLUMN_ORDERNUMBER + " TEXT," + COLUMN_DATEORDERED + " TIMESTAMP,"
            + COLUMN_USERID +
            " INTEGER REFERENCES " + TABLE_USERS + "(" + COLUMN_USERID
            + "))";

    // Items Table
    public static final String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS
            + "(" + COLUMN_ITEMID + " INTEGER PRIMARY KEY,"
            + COLUMN_UPC + " BIGINT,"
            + COLUMN_PRICE + " DECIMAL,"
            + COLUMN_TYPEID + " INTEGER REFERENCES " + TABLE_TYPES + "(" + COLUMN_TYPEID + ") ,"
            + COLUMN_SIZE + " INTEGER,"
            + COLUMN_DESCRIPTION + " TEXT)";

    // OrderItems Table
    public static final String CREATE_ITEMORDERS_TABLE = "CREATE TABLE " +
            TABLE_ITEMORDERS + "(" + COLUMN_ORDERID + " INTEGER REFERENCES " +
            TABLE_ORDERS + "(" + COLUMN_ORDERID + ")," + COLUMN_ITEMID +
            " INTEGER REFERENCES " + TABLE_ITEMS + "(" + COLUMN_ITEMID + "),"
            + COLUMN_LASTNAME + " INTEGER)";

    // Palettes Table
    public static final String CREATE_PALETTES_TABLE = "CREATE TABLE " +
            TABLE_PALETTES + "(" + COLUMN_PALETTEID + " INTEGER PRIMARY KEY," +
            COLUMN_PALETTENAME + " TEXT," + COLUMN_TIMESTAMP + " TIMESTAMP,"
            + COLUMN_USERID +
            " INTEGER REFERENCES " + TABLE_USERS + "(" + COLUMN_USERID
            + "))";

    // Types Table
    public static final String CREATE_TYPES_TABLE = "CREATE TABLE " +
            TABLE_TYPES + "(" + COLUMN_TYPEID + " INTEGER PRIMARY KEY,"
            + COLUMN_ITEMTYPE + " VARCHAR(50))";


    // Colors Table
    public static final String CREATE_COLORS_TABLE = "CREATE TABLE " +
            TABLE_COLORS + "(" + COLUMN_HEXVALUE + " INTEGER PRIMARY KEY,"
            + COLUMN_TIMESTAMP + " TIMESTAMP)";

    // PaletteColors Table
    public static final String CREATE_PALETTECOLORS_TABLE = "CREATE TABLE " +
            TABLE_PALETTECOLORS + "(" + COLUMN_PALETTEID + " INTEGER REFERENCES " +
            TABLE_PALETTES + "(" + COLUMN_PALETTEID + ")," + COLUMN_HEXVALUE +
            " INTEGER REFERENCES " + TABLE_COLORS + "(" + COLUMN_HEXVALUE + "))";

    // FavoriteColors Table
    public static final String CREATE_FAVORITECOLORS_TABLE = "CREATE TABLE " +
            TABLE_FAVORITECOLORS + "(" + COLUMN_USERID + " INTEGER REFERENCES " +
            TABLE_USERS + "(" + COLUMN_USERID + ")," + COLUMN_HEXVALUE +
            " INTEGER REFERENCES " + TABLE_COLORS + "(" + COLUMN_HEXVALUE + "),"
            + COLUMN_COLORNAME + " TEXT)";

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
        db.execSQL(CREATE_TYPES_TABLE);
        Log.d("DATABASE", "onCreate: tables created");
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPES);
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_ORDERS_TABLE);
        db.execSQL(CREATE_ITEMS_TABLE);
        db.execSQL(CREATE_ITEMORDERS_TABLE);
        db.execSQL(CREATE_COLORS_TABLE);
        db.execSQL(CREATE_PALETTES_TABLE);
        db.execSQL(CREATE_PALETTECOLORS_TABLE);
        db.execSQL(CREATE_FAVORITECOLORS_TABLE);
        db.execSQL(CREATE_FAVORITEPALETTES_TABLE);
        db.execSQL(CREATE_TYPES_TABLE);

    }

    /**
     * Create Operations
     */

    public boolean addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, user.getFirstName());
        values.put(COLUMN_LASTNAME, user.getLastName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_RECOVERYEMAIL, user.getRecoveryEmail());
        values.put(COLUMN_PHONENUMBER, user.getPhoneNumber());
        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
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
        values.put(COLUMN_PRICE, item.getPrice());
        values.put(COLUMN_TYPEID, item.getItemTypeId());
        values.put(COLUMN_SIZE, item.getSize());
        values.put(COLUMN_DESCRIPTION, item.getDescription());
        db.insert(TABLE_ITEMS, null, values);
        Log.d("SIZE", "item added successfuly");
        db.close();
    }

    public void addType(String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEMTYPE, type);
        db.insert(TABLE_TYPES, null, values);
        Log.d("SIZE", "type added successfuly");
        db.close();
    }

    /**
     * Adding a color to the colors table
     * @param color the color being added
     * @return
     */
    public boolean addColor(Color color){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_HEXVALUE, color.getHexValue());
        values.put(COLUMN_TIMESTAMP, color.getTimestamp());
        long result = -1;
        try {
            result = db.insertOrThrow(TABLE_COLORS, null, values);
        }catch (SQLiteConstraintException e){
            Log.d("COLORPICKER", "Color already in table");
            boolean checkFavColorTable = isFavouriteColorOnDatabase(color.getUserId(), color.getHexValue());
            if(checkFavColorTable){
                Log.d("COLORPICKER", "Color is already is favourites table");
            }else {
                boolean secondResult = addFavoriteColor(color, color.getUserId());
                if (secondResult) {
                    Log.d("COLORPICKER", "Color successfully added on the favourites table for user " + color.getUserId());
                }
            }
        }finally {
            db.close();
        }
        if (result == -1)
            return false;
        else
            return true;
    }

    /**
     * This method adds the color id and the user id to the linking table
     * @param color
     * @param userId
     * @return
     */
    public boolean addFavoriteColor(Color color, int userId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERID, userId);
        values.put(COLUMN_HEXVALUE, color.getHexValue());
        values.put(COLUMN_COLORNAME, color.getColorName());
        long result = db.insert(TABLE_FAVORITECOLORS, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    public void addPalette(Palette palette){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PALETTEID, palette.getPaletteID());
        values.put(COLUMN_USERID, palette.getUserID());
        values.put(COLUMN_PALETTENAME, palette.getPaletteName());
        values.put(COLUMN_TIMESTAMP, palette.getTimestamp());
        db.insert(TABLE_PALETTES, null, values);
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
                    cursor.getString(5),
                    cursor.getString(6));
        }
        db.close();
        return user;
    }

    public User getUser(String email, String password){
        SQLiteDatabase db = null;
        User user = null;
        Cursor cursor = null;
        try {
            db = this.getReadableDatabase();
            cursor = db.query(TABLE_USERS,
                    new String[]{COLUMN_USERID, COLUMN_FIRSTNAME, COLUMN_LASTNAME, COLUMN_EMAIL, COLUMN_PASSWORD,
                            COLUMN_RECOVERYEMAIL, COLUMN_PHONENUMBER},
                    COLUMN_EMAIL + "=?" + " AND " + COLUMN_PASSWORD + "=?", new String[]{email, password},
                    null, null, null, "1");
            if (cursor != null && cursor.moveToFirst()) {
                user = new User(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6));
            }
        }catch (final Exception e){
            Log.d("DATABASE", "something went wrong");
        }finally {
            db.close();
            cursor.close();
        }
        return  user;
    }


    public Boolean getUser(String email){
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = this.getReadableDatabase();
            cursor = db.query(TABLE_USERS,
                    new String[]{COLUMN_USERID, COLUMN_FIRSTNAME, COLUMN_LASTNAME, COLUMN_EMAIL, COLUMN_PASSWORD,
                            COLUMN_RECOVERYEMAIL, COLUMN_PHONENUMBER},
                    COLUMN_EMAIL + "=?", new String[]{email},
                    null, null, null, "1");
            if (cursor != null && cursor.moveToFirst()) {
                return true;
            }
        }catch (final Exception e){
            Log.d("DATABASE", "something went wrong");
        }finally {
            assert db != null;
            db.close();
            assert cursor != null;
            cursor.close();
        }
        return false;
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
                        cursor.getString(5),
                        cursor.getString(6)));
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
    public ArrayList<Item> getItems(String typeId, String size){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Item> items = new ArrayList<>();
        Cursor cursor = db.query(TABLE_ITEMS,
                new String[]{COLUMN_ITEMID, COLUMN_UPC, COLUMN_PRICE, COLUMN_TYPEID, COLUMN_SIZE, COLUMN_DESCRIPTION},
                COLUMN_TYPEID + "=? AND " + COLUMN_SIZE + "=?", new String[]{typeId, size},
                null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                items.add(new Item(Integer.parseInt(cursor.getString(0)),
                        Integer.parseInt(cursor.getString(1)),
                        Double.parseDouble(cursor.getString(2)),
                        Integer.parseInt(cursor.getString(3)),
                        Integer.parseInt(cursor.getString(4)),
                        cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return items;
    };


    /*
    Get the sizes available in the table

    I only get them by one Id because we know there are all the same sizes
     */
    public ArrayList<String> getSizes(String typeId, String description){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> sizes = new ArrayList<>();
        Cursor cursor = db.query(TABLE_ITEMS,
                new String[]{COLUMN_SIZE},
                COLUMN_TYPEID + "=? AND " + COLUMN_DESCRIPTION + "=?", new String[]{typeId, description},
                null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                sizes.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        return sizes;
    }

    /**
     * This method purpose is to check the favourite colours table to see if the selected color is already in the table
     *
     * @param user_id
     * @param colorHex
     * @return
     */
    public Boolean isFavouriteColorOnDatabase(int user_id, int colorHex){
        SQLiteDatabase db = this.getReadableDatabase();
        //table name, String Array of column names, query, String array of values that will
        // be inserted into the query
        Cursor cursor = db.query(TABLE_FAVORITECOLORS,
                new String[]{COLUMN_USERID, COLUMN_HEXVALUE, COLUMN_COLORNAME},
                COLUMN_USERID + "=?" + " AND " + COLUMN_HEXVALUE + "=?", new String[]{String.valueOf(user_id), String.valueOf(colorHex)},
                null, null, null, null);
        if(cursor != null){
            if (cursor.moveToFirst()) {
                    Log.d("GETCOLORS",Integer.parseInt(cursor.getString(1))+ " was on the table");
                    db.close();
                    return true;
            }
        }
        db.close();
        return false;
    }

    public ArrayList<Color> getAllFavouriteColours(User user, String limit){
        ArrayList<Color> colorList = new ArrayList<Color>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FAVORITECOLORS,
                new String[]{COLUMN_USERID, COLUMN_HEXVALUE, COLUMN_COLORNAME},
                COLUMN_USERID + "=?", new String[]{String.valueOf(user.getUserID())} , null, null, null, limit);
        if(cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    colorList.add(new Color(Integer.parseInt(cursor.getString(0)),
                            Integer.parseInt(cursor.getString(1)),
                            cursor.getString(2)));
                    Log.d("GETFAVORITECOLORS",Integer.parseInt(cursor.getString(1))+ " added to the list");
                } while (cursor.moveToNext());
            }else{
                Log.d("GETFAVORITECOLORS","No colours retrieved from the cursor");
            }
        }
        return colorList;
    }

    public ArrayList<Color> getAllColors() {
        ArrayList<Color> colorList = new ArrayList<Color>();
        String query = "SELECT * FROM " + TABLE_COLORS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                colorList.add(new Color(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3))));
            } while (cursor.moveToNext());
        }

        db.close();
        return colorList;
    }


    // Palettes
    public Palette getPalette(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Palette palette = null;
        //table name, String Array of column names, query, String array of values that will
        // be inserted into the query
        Cursor cursor = db.query(TABLE_PALETTES,
                new String[]{COLUMN_PALETTEID, COLUMN_USERID, COLUMN_PALETTENAME, COLUMN_TIMESTAMP},
                COLUMN_HEXVALUE + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            palette = new Palette(Integer.parseInt(cursor.getString(0)),
                    Integer.parseInt(cursor.getString(1)),
                    cursor.getString(2),
                    cursor.getString(3));
        }
        db.close();
        return palette;
    }

    public ArrayList<Palette> getAllPalettes() {
        ArrayList<Palette> paletteList = new ArrayList<Palette>();
        String query = "SELECT * FROM " + TABLE_PALETTES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                paletteList.add(new Palette(Integer.parseInt(cursor.getString(0)),
                        Integer.parseInt(cursor.getString(1)),
                        cursor.getString(2),
                        cursor.getString(3)));
            } while (cursor.moveToNext());
        }

        db.close();
        return paletteList;
    }

    /**
     * UPDATE OPERATIONS
     */


    public int updateUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, user.getFirstName());
        values.put(COLUMN_LASTNAME, user.getLastName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_RECOVERYEMAIL, user.getRecoveryEmail());
        values.put(COLUMN_PHONENUMBER, user.getPhoneNumber());
        return db.update(TABLE_USERS, values, COLUMN_USERID + "= ?",
                new String[]{String.valueOf(user.getUserID())});
    }

    // Order
    public int updateOrder(Order order){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDERNUMBER, order.getOrderNumber());
        values.put(COLUMN_DATEORDERED, order.getDateOrdered());;
        return db.update(TABLE_ORDERS, values, COLUMN_ORDERID + "= ?",
                new String[]{String.valueOf(order.getOrderID())});
    }

    // Item
    public int updateItem(Item item){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_UPC, item.getUpc());
        values.put(COLUMN_PRICE, item.getPrice());
        values.put(COLUMN_DESCRIPTION, item.getDescription());;
        values.put(COLUMN_TYPEID, item.getItemTypeId());;
        values.put(COLUMN_SIZE, item.getSize());;
        return db.update(TABLE_ITEMS, values, COLUMN_ITEMID + "= ?",
                new String[]{String.valueOf(item.getItemID())});
    }

    // Color
    public int updateColor(Color color, String newName){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COLORNAME, newName);
        return db.update(TABLE_FAVORITECOLORS, values, COLUMN_HEXVALUE + "= ?",
                new String[]{String.valueOf(color.getHexValue())});
    }

    // Palette
    public int updatePalette(Palette palette){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PALETTENAME, palette.getPaletteName());
        values.put(COLUMN_TIMESTAMP, palette.getTimestamp());
        return db.update(TABLE_PALETTES, values, COLUMN_PALETTEID + "= ?",
                new String[]{String.valueOf(palette.getPaletteID())});
    }



    /**
     * DELETE OPERATIONS
     */

    // User
    public void deleteUser(int user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, COLUMN_USERID + " = ?",
                new String[]{String.valueOf(user)});
        db.close();
    }

    // Order
    public void deleteOrder(int order){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ORDERS, COLUMN_ORDERID + " = ?",
                new String[]{String.valueOf(order)});
        db.close();
    }

    // Item
    public void deleteItem(int item){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ITEMS, COLUMN_ITEMID + " = ?",
                new String[]{String.valueOf(item)});
        db.close();
    }

    // Color
    public void deleteColor(Color color){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVORITECOLORS, COLUMN_HEXVALUE + " =? AND " + COLUMN_USERID + " =?"  ,
                new String[]{String.valueOf(color.getHexValue()), String.valueOf(color.getUserId())});
        db.close();
    }

    // Palette
    public void deletePalette(int palette){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PALETTES, COLUMN_HEXVALUE + " = ?",
                new String[]{String.valueOf(palette)});
        db.close();
    }


    public void deleteAllItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_ITEMS);
        db.close();
    }

    public void deleteAllTypes() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_TYPES);
        db.close();
    }
}
