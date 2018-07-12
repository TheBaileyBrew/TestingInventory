package com.thebaileybrew.testinginventory.data;

import android.provider.BaseColumns;

public final class ModelContract {

    private ModelContract() {}

    public static final class ModelEntry implements BaseColumns {
        public final static String TABLE_NAME = "models";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_MODEL_TYPE = "model";
    }
}
