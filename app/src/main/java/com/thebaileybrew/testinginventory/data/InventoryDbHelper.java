package com.thebaileybrew.testinginventory.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.thebaileybrew.testinginventory.data.InventoryContract.*;
import static com.thebaileybrew.testinginventory.data.ModelContract.*;
import static com.thebaileybrew.testinginventory.data.UserContract.*;

public class InventoryDbHelper extends SQLiteOpenHelper {

    public static final String TAG = InventoryDbHelper.class.getSimpleName();

    //Name of the Database file
    private static final String DATABASE_NAME = "inventory.db";

    //Definition of starting Database version. When Schema changes, database version must be incremented.
    private static final int DATABASE_VERSION = 1;

    //Construct the instance of the Database
    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    //Called when the Database is first created
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creates a string that contains the SQL statement to create the inventory table
        String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE IF NOT EXISTS " + InventoryEntry.TABLE_NAME + "("
                + InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InventoryEntry.COLUMN_USER_ADD + " TEXT, "
                + InventoryEntry.COLUMN_USER_ADD_DATE + " TEXT NOT NULL, "
                + InventoryEntry.COLUMN_USER_UPDATE + " TEXT, "
                + InventoryEntry.COLUMN_USER_UPDATE_DATE + " TEXT, "
                + InventoryEntry.COLUMN_INVENTORY_TYPE + " INTEGER NOT NULL DEFAULT 0, "
                + InventoryEntry.COLUMN_MODEL + " INTEGER NOT NULL DEFAULT 0, "
                + InventoryEntry.COLUMN_MODEL_TYPE + " INTEGER NOT NULL DEFAULT 0, "
                + InventoryEntry.COLUMN_MODEL_SERIAL_NUMBER + " TEXT );";

        //Creates a string that contains the SQL statement to create the model table
        //String SQL_CREATE_MODEL_TABLE = "CREATE TABLE IF NOT EXISTS " + ModelEntry.TABLE_NAME + "("
        //        + ModelEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        //        + ModelEntry.COLUMN_MODEL_TYPE + " TEXT NOT NULL DEFAULT 0);";

        //Creates a string that contains the SQL statement to create the user table
        //String SQL_CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + UserEntry.TABLE_NAME + "("
        //        + UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        //        + UserEntry.COLUMN_USER_NAME + " TEXT NOT NULL, "
        //        + UserEntry.COLUMN_USER_PASSWORD + " TEXT, "
        //        + UserEntry.COLUMN_USER_ADMIN + " INTEGER NOT NULL DEFAULT 0, "
        //        + UserEntry.COLUMN_USER_LAST_UPDATE + " TEXT );";

        //Execute the SQL statement
        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
        //Execute the SQL creation for models
        //db.execSQL(SQL_CREATE_MODEL_TABLE);
        //Execute the SQL creation for users
        //db.execSQL(SQL_CREATE_USER_TABLE);
    }

    //Called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Current database is at version 1, so no update needed
    }
}
