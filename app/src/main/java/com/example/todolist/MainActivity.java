package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private DataHelper mhelper=new DataHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton listiner=(FloatingActionButton) findViewById(R.id.Click);
        listiner.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,GetData.class);
                startActivity(intent);
                //insert();displayDBInfo();
            }
        });

    }
    private void insert(){
        SQLiteDatabase DB=mhelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Schema.entries.title,"Hello");
        values.put(Schema.entries.Entry,"World");

        long status=DB.insert(Schema.entries.Table_Name,null,values);
        Log.v("Activity"," New RowID"+status);
    }
    private void displayDBInfo(){
        DataHelper helper=new DataHelper(this);
        SQLiteDatabase DB=helper.getReadableDatabase();
        Cursor cursor=DB.rawQuery("SELECT * FROM "+ Schema.entries.Table_Name,null);

        try {
            TextView display=(TextView) findViewById(R.id.title);
            display.setText("Number of rows in the Table "+cursor.getCount());
        }finally{
            cursor.close();
        }
    }
}