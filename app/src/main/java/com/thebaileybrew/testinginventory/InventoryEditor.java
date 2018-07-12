package com.thebaileybrew.testinginventory;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.thebaileybrew.testinginventory.data.InventoryDbHelper;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static com.thebaileybrew.testinginventory.data.InventoryContract.*;

public class InventoryEditor extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = InventoryEditor.class.getSimpleName();

    //EditText field for Device Model
    private EditText modelEntryEditText;

    //EditText field for Serial Number
    private EditText modelSerialEditText;

    //Spinner field for Model Usage Type
    private Spinner modelTypeSpinner;

    //Spinner field for Model Inventory Type
    private Spinner modelInventorySpinner;

    //Floating Action Button
    private FloatingActionButton floatingAdd;

    SQLiteDatabase db;

    /*
    * Define the device type. Possible valid values are in InventoryContract.java
    * (@Link InventoryEntry#CUSTOMER_TYPE), (@Link InventoryEntry#SALES_TYPE) or
    * (@Link InventoryEntry#IT_TYPE).
    */
    private int modelType = InventoryEntry.CUSTOMER_TYPE;

    /*
     * Define the device type. Possible valid values are in InventoryContract.java
     * (@Link InventoryEntry#TYPE_NEW), (@Link InventoryEntry#TYPE_USED) or
     * (@Link InventoryEntry#TYPE_TEST).
     */
    private int inventoryType = InventoryEntry.TYPE_NEW;

    /*
    * Define the User who is creating the reference
    */
    private String userName = "Matthew Bailey";




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_editor);

        modelEntryEditText = findViewById(R.id.model_name_edittext);
        modelSerialEditText = findViewById(R.id.model_serial_edittext);
        modelTypeSpinner = findViewById(R.id.model_usage_spinner);
        modelInventorySpinner = findViewById(R.id.model_inventory_type_spinner);
        floatingAdd = findViewById(R.id.floating_add);
        floatingAdd.setOnClickListener(this);

        setupSpinners();
    }

    private void setupSpinners() {
        //Create the adapter for each spinner. The list of options are pulled from a String Array
        //Spinner 'currently' uses a default layout
        //TODO: Look into potential custom layouts
        ArrayAdapter modelTypeAdapter = ArrayAdapter.createFromResource(this, R.array.array_model_types, android.R.layout.simple_spinner_item);
        ArrayAdapter modelInventoryAdapter = ArrayAdapter.createFromResource(this, R.array.array_inventory_types, android.R.layout.simple_spinner_item);
        //Creates the dropdown style for the Spinner
        //TODO: Look into custom drop down style
        modelTypeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        modelInventoryAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        //Apply the adapter to the spinner
        modelTypeSpinner.setAdapter(modelTypeAdapter);
        modelInventorySpinner.setAdapter(modelInventoryAdapter);

        //Sets the integer value to the constant values
        modelTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             *
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                if(TextUtils.isEmpty(selectedItem)){
                    if(selectedItem.equals(String.valueOf(R.string.customer_type))) {
                        modelType = InventoryEntry.CUSTOMER_TYPE;
                    } else if (selectedItem.equals(String.valueOf(R.string.sales_type))) {
                        modelType = InventoryEntry.SALES_TYPE;
                    } else {
                        modelType = InventoryEntry.IT_TYPE;
                    }
                }
            }

            /**
             *
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                modelType = InventoryEntry.CUSTOMER_TYPE;
            }
        });

        //Sets the integer value to the constant values
        modelInventorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                if(TextUtils.isEmpty(selectedItem)){
                    if(selectedItem.equals(String.valueOf(R.string.test_type))){
                        inventoryType = InventoryEntry.TYPE_TEST;
                    } else if (selectedItem.equals(String.valueOf(R.string.new_type))) {
                        inventoryType = InventoryEntry.TYPE_NEW;
                    } else {
                        inventoryType = InventoryEntry.TYPE_USED;
                    }
                }
            }

            /**
             *
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                inventoryType = InventoryEntry.TYPE_NEW;
            }
        });

    }
    /*
    * Get the user input and add new inventory to the Database
    */


    private void insertInventory() {
        //Initialize the Database in readable mode
        InventoryDbHelper InventoryDB = new InventoryDbHelper(this);
        //TODO: Can a Database be in both Readable & Writeable at the same time?
        db = InventoryDB.getReadableDatabase();

        //Read from the input fields
        //Trim potential white space
        String modelNameString = modelEntryEditText.getText().toString().trim();
        String modelSerialString = modelSerialEditText.getText().toString().trim();

        //Get the current time of database add
        SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/yyyy - HH:mm:ss");
        String currentDateAndTime = sdf.format(new Date());

        //Validate EditText Entryfields
        Log.i(TAG, "insertInventory - model added: " + modelNameString);
        Log.i(TAG, "insertInventory - serial added: " + modelSerialString);
        if (!Arrays.asList(R.array.array_devices).contains(modelNameString)) {
            modelEntryEditText.setTextColor(Color.RED);
            Toast.makeText(this, "Please enter a valid Device Model", Toast.LENGTH_SHORT).show();
        }

        //Pass the serial number through the database for validation
        //TODO: Figure out validation for serial number in database
        //if (checkForSerialInDatabase(modelSerialString)) {
        //    modelSerialEditText.setTextColor(Color.GREEN);
        //} else {
        //    modelSerialEditText.setTextColor(Color.RED);
        //    Toast.makeText(this, "Serial Number already exists", Toast.LENGTH_SHORT).show();
        //}

        //Allow for writeability to the Database
        db = InventoryDB.getWritableDatabase();

        //Create a ContentValues object with column names are the keys and received data are the values
        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_MODEL, modelNameString);
        values.put(InventoryEntry.COLUMN_MODEL_SERIAL_NUMBER, modelSerialString);
        values.put(InventoryEntry.COLUMN_MODEL_TYPE, modelType);
        values.put(InventoryEntry.COLUMN_INVENTORY_TYPE, inventoryType);
        values.put(InventoryEntry.COLUMN_USER_ADD, userName);
        values.put(InventoryEntry.COLUMN_USER_ADD_DATE, currentDateAndTime);

        //Insert a new row for the database add
        long newRowId = db.insert(InventoryEntry.TABLE_NAME, null, values);

        //Display a toast for successful add
        if (newRowId == -1) {
            Toast.makeText(this, "Error saving hardware", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Hardware added successfully" + newRowId, Toast.LENGTH_SHORT).show();
        }




    }


    //Validates if Serial Number already in Database
    //public boolean checkForSerialInDatabase(String serial) {
    //    InventoryDbHelper tempDbHelper = new InventoryDbHelper(this);
    //    SQLiteDatabase tempDb = tempDbHelper.getReadableDatabase();
    //    Cursor cursor = tempDb.rawQuery("SELECT * FROM hardware where serialnumber = " + serial +"", null);
    //    if (cursor.getCount() <=0) {
    //        cursor.close();
    //        return false;
    //    } else {
    //       cursor.close();
    //        return true;
    //    }
    //}


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.floating_add:
                insertInventory();
                finish();
                break;
        }
    }
}
