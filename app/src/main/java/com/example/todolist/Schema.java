package com.example.todolist;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Schema {
    private Schema(){}

    public static final String Content_Authority="com.example.android.DataBase";

    public static final Uri Base_Content_Uri=Uri.parse("content://"+Content_Authority);
    public static final String PATH="/TestDatabase";


    public static final Uri Content_Uri=Uri.withAppendedPath(Base_Content_Uri,"GetData");
    public static final Uri Insert_Uri=Uri.withAppendedPath(Base_Content_Uri,"Insert");
    public static final Uri GetId=Uri.withAppendedPath(Base_Content_Uri,"GetData/Id");

    public static final class entries implements BaseColumns{
        public static final String Table_Name= "TestDatabase";
        public static final String _ID=BaseColumns._ID;
        public static final String title="DataEntry1";
        public static final String Entry="DataEntry2";

    }
}