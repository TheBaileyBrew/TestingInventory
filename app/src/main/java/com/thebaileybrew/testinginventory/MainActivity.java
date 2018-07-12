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
    }


}
