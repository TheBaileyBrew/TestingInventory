package com.thebaileybrew.testinginventory.data;

import android.provider.BaseColumns;

public class UserContract {

    //Stops users from accidentally instantiating the contract class
    private UserContract() {}

    //Inner class defines the contants for the User Table
    public static final class UserEntry implements BaseColumns {

        //Name of Database Table for users
        public final static String TABLE_NAME = "users";

        //Unique ID number for table
        //Type: INTEGER
        public final static String _ID = BaseColumns._ID;

        //User Name
        //Type: TEXT
        public final static String COLUMN_USER_NAME = "username";

        //User Permission
        //Type: INTEGER
        public final static String COLUMN_USER_ADMIN = "adminmode";
        public static final int ADMIN_YES = 0;
        public static final int ADMIN_NO = 1;

        //User Password
        //Type: TEXT
        public final static String COLUMN_USER_PASSWORD = "password";

        //User Last Update
        //Type: TEXT
        public final static String COLUMN_USER_LAST_UPDATE = "lastupdate";
    }
}
