package com.example.todolist;
import com.example.todolist.Schema.entries;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DatabaseName="Test.db";
    private static final int Version=1;
    public DataHelper(Context context){
        super(context,DatabaseName,null,Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Table=
                "CREATE TABLE "+entries.Table_Name+"("+
                        entries._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        entries.title+ " TEXT NOT NULL,"+
                        entries.Entry+ " TEXT DEFAULT NULL);";
        db.execSQL(Create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
