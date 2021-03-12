package com.example.todolist;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * {@link ContentProvider} for Pets app.
 */
public class DbContentProvider extends ContentProvider {

    /** Tag for the log messages */
    private DataHelper mhelper;


    public static final int GetTable=100;
    public static final int Get_Id=101;
    public static final int Insert=102;
    public static final int Edit=103;
    public static final int Del=104;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        // The content URI of the form "content://com.example.android.pets/pets" will map to the
        // integer code {@link #PETS}. This URI is used to provide access to MULTIPLE rows
        // of the pets table.
        sUriMatcher.addURI(Schema.Content_Authority,"GetData",GetTable);
        sUriMatcher.addURI(Schema.Content_Authority,"Insert",Insert);
        sUriMatcher.addURI(Schema.Content_Authority,"GetData/Id",Get_Id);
        sUriMatcher.addURI(Schema.Content_Authority,"Insert/Id",Edit);
        sUriMatcher.addURI(Schema.Content_Authority,"Del/Id",Del);

        // The content URI of the form "content://com.example.android.pets/pets/#" will map to the
        // integer code {@link #PETS_ID}. This URI is used to provide access to ONE single row
        // of the pets table.

        // In this case, the "#" wildcard is used where "#" can be substituted for an integer.
        // For example, "content://com.example.android.pets/pets/3" matches, but
        // "content://com.example.android.pets/pets" (without a number at the end) doesn't match.
    }
    /**
     * Initialize the provider and the database helper object.
     */
    @Override
    public boolean onCreate() {
        mhelper=new DataHelper(getContext());
        return true;
    }

    /**
     * Perform the query for the given URI. Use the given projection, selection, selection arguments, and sort order.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        Cursor cursor;
        SQLiteDatabase db=mhelper.getReadableDatabase();
        int match=sUriMatcher.match(uri);
        switch(match){
            case GetTable:
                cursor=db.query(Schema.entries.Table_Name,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case Get_Id:
                selection=Schema.entries._ID+"=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor=db.query(Schema.entries.Table_Name,projection,selection,selectionArgs,null,null,sortOrder);

                break;
            default:
                throw new SQLException("Failed To Load Data From DataBase: "+uri);

        }
        return cursor;
    }

    /**
     * Insert new data into the provider with the given ContentValues.
     */
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        Uri InsertUri=null;
        if(mhelper==null){
            mhelper=new DataHelper(getContext());
        }
        final SQLiteDatabase db=mhelper.getWritableDatabase();
        switch(sUriMatcher.match(uri)){
            case Insert:
                long status=db.insert(Schema.entries.Table_Name,null,contentValues);
                if(status>0){
                    InsertUri=ContentUris.withAppendedId(Schema.Insert_Uri,status);
                    getContext().getContentResolver().notifyChange(InsertUri,null);
                }
                break;


            default:
                throw new SQLException("Failed To Insert Row Into "+uri);

        }

        return InsertUri;
    }

    /**
     * Updates the data at the given selection and selection arguments, with the new ContentValues.
     */
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        int status ;
        final SQLiteDatabase db=mhelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)){
            case Edit:
                status =db.update(Schema.entries.Table_Name,contentValues,selection,selectionArgs);
                if(status>0) {
                    Log.i("Test", "Updated");

                    getContext().getContentResolver().notifyChange(ContentUris.withAppendedId(Schema.Update_Id,status),null);

                }
                break;
            default:
                throw new SQLException("Failed To Update Row Into "+uri);

        }
        return status;
    }

    /**
     * Delete the data at the given selection and selection arguments.
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int status ;
        final SQLiteDatabase db=mhelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)){
            case Del:
                status =db.delete(Schema.entries.Table_Name,selection,selectionArgs);
                if(status>0) {
                    Log.i("Test", "Deleted");

                    getContext().getContentResolver().notifyChange(ContentUris.withAppendedId(Schema.Del_Id,status),null);
                }
                break;
            default:
                throw new SQLException("Failed To Update Row Into "+uri);

        }
        return status;
    }

    /**
     * Returns the MIME type of data for the content URI.
     */
    @Override
    public String getType(Uri uri) {
        return null;
    }
}


