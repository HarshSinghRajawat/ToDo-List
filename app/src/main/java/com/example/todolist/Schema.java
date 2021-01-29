package com.example.todolist;

import android.provider.BaseColumns;

public final class Schema {
    private Schema(){}

    public static final class entries implements BaseColumns{
        public static final String Table_Name= "TestDatabase";
        public static final String _ID=BaseColumns._ID;
        public static final String title="DataEntry1";
        public static final String Entry="DataEntry2";
    }
}