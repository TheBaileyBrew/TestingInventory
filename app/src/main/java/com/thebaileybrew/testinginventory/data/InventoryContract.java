package com.thebaileybrew.testinginventory.data;

import android.provider.BaseColumns;

public class InventoryContract {

    //Stops users from accidentally instatiating the contract class
    private InventoryContract() {}

    /*
    * Inner class defines the constant values for the Inventory Table
    * Each entry in the table represents a single item.
    */
    public static final class InventoryEntry implements BaseColumns {

        //Name of Database Table for inventory
        public final static String TABLE_NAME = "hardware";

        /*
        * Unique ID number for table
        * Type: INTEGER
        */
        public final static String _ID = BaseColumns._ID;

        /*
        * Type of inventory item
        * The only possible values are @Link #CUSTOMER_TYPE, @Link #SALES_TYPE, @LINK #IT_TYPE
        * Type: INTEGER
        */
        public final static String COLUMN_MODEL_TYPE = "type";
        public static final int CUSTOMER_TYPE = 0;
        public static final int SALES_TYPE = 1;
        public static final int IT_TYPE = 2;

        /*
        * Model of equipment
        * The only possible values are @Link #MOTOROLA_MC3100, @Link #MOTOROLA_MC3200,
        * @Link #UNITECH_HT682, @Link #APPLE_IPHONE, @Link #APPLE_IPAD
        * Type: INTEGER
        */
        public final static String COLUMN_MODEL = "model";
        public static final int MOTOROLA_MC3100 = 0;
        public static final int MOTOROLA_MC3200 = 1;
        public static final int UNITECH_HT682 = 2;
        public static final int APPLE_IPHONE = 3;
        public static final int APPLE_IPAD = 4;

        /*
        * Type of Inventory
        * The only possible values are @Link #TYPE_NEW, @Link #TYPE_USED, @Link #TYPE_TEST
        *
        * Type: INTEGER
        */
        public final static String COLUMN_INVENTORY_TYPE = "source";
        public static final int TYPE_NEW = 0;
        public static final int TYPE_USED = 1;
        public static final int TYPE_TEST = 2;

        /*
        * Name of User Adding Device
        *
        * Type: TEXT
        */
        public final static String COLUMN_USER_ADD = "addUser";

        /*
        * Date of User Adding Device
        *
        * Type: TEXT
        */
        public final static String COLUMN_USER_ADD_DATE = "addDate";

        /*
        * Name of User Updating Device
        *
        * Type: TEXT
        */
        public final static String COLUMN_USER_UPDATE = "updateUser";

        /*
        * Date of User Updating Device
        *
        * Type: TEXT
        */
        public final static String COLUMN_USER_UPDATE_DATE = "updateDate";

        /*
        * Serial Number of Device
        *
        * Type: TEXT
        */
        public final static String COLUMN_MODEL_SERIAL_NUMBER = "serialnumber";
    }
}
