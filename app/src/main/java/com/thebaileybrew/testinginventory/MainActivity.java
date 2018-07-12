package com.thebaileybrew.testinginventory;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.thebaileybrew.testinginventory.data.InventoryContract;
import com.thebaileybrew.testinginventory.data.InventoryDbHelper;

import static com.thebaileybrew.testinginventory.data.InventoryContract.*;

public class MainActivity extends AppCompatActivity {

    //Database helper that allows access to the database
    private InventoryDbHelper inventoryDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up the FAB to open Add Item Menu
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addInventory = new Intent(MainActivity.this,InventoryEditor.class);
                startActivity(addInventory);
            }
        });

        inventoryDbHelper = new InventoryDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseDetails();
    }

    //Temporary helper to display information in the TextView about the state of the Database
    private void displayDatabaseDetails() {
        SQLiteDatabase db = inventoryDbHelper.getReadableDatabase();

        //Defining a projection that specifies which columns should be displayed
        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_MODEL,
                InventoryEntry.COLUMN_MODEL_SERIAL_NUMBER,
                InventoryEntry.COLUMN_INVENTORY_TYPE,
                InventoryEntry.COLUMN_USER_ADD,
                InventoryEntry.COLUMN_USER_ADD_DATE};

        //Perform a query on the Database Table
        Cursor cursor = db.query(
                InventoryEntry.TABLE_NAME,      //The table to query
                projection,                     //The columns to return
                null,                  //The colums for the WHERE clause
                null,               //The values for the WHERE clause
                null,                  //How return should be grouped
                null,                    //How group return should be filtered
                null);                  //The sorting order

        TextView displayView = (TextView) findViewById(R.id.database_details_textview);

        //Perform a Try to create the display details
        try{

            displayView.setText("The Inventory Table contains " + cursor.getCount() + " items.\n\n");
            displayView.append(InventoryEntry._ID + " - " +
                    InventoryEntry.COLUMN_MODEL + " - " +
                    InventoryEntry.COLUMN_MODEL_SERIAL_NUMBER + " - " +
                    InventoryEntry.COLUMN_INVENTORY_TYPE + " - " +
                    InventoryEntry.COLUMN_USER_ADD + " - " +
                    InventoryEntry.COLUMN_USER_ADD_DATE + "\n");

            //Figure out the index for columns
            int idColumnIndex = cursor.getColumnIndex(InventoryEntry._ID);
            int modelColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_MODEL);
            int serialColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_MODEL_SERIAL_NUMBER);
            int invTypeColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_TYPE);
            int userAddColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_USER_ADD);
            int userAddDateColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_USER_ADD_DATE);

            //Iterate through all the returned rows in the database
            while (cursor.moveToNext()) {
                //Use the index to extract the String/Int values at the current row of the cursor
                int currentId = cursor.getInt(idColumnIndex);
                String currentModel = cursor.getString(modelColumnIndex);
                String currentSerial = cursor.getString(serialColumnIndex);
                int currentInvType = cursor.getInt(invTypeColumnIndex);
                String currentUserAdd = cursor.getString(userAddColumnIndex);
                String currentUserAddDate = cursor.getString(userAddDateColumnIndex);
                displayView.append("\n" + currentId + " - " +
                        currentModel + " - " +
                        currentSerial + " - " +
                        currentInvType + " - " +
                        currentUserAdd + " - " +
                        currentUserAddDate);
            }
        } finally {
            cursor.close();
        }
    }
}
