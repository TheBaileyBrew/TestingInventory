package com.thebaileybrew.testinginventory.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.thebaileybrew.testinginventory.data.InventoryContract.*;
import static com.thebaileybrew.testinginventory.data.ModelContract.*;
import static com.thebaileybrew.testinginventory.data.UserContract.*;

public class InventoryDbHelper extends SQLiteOpenHelper {


    /**
     *
     */
    public InventoryDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Called when the Database is first created
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    //Called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Current database is at version 1, so no update needed
    }
}
