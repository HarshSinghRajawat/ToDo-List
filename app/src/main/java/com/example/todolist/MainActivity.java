package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView mEmptyStateTextView;

    @Override
    protected void onStart() {
        super.onStart();
        String[] projection={Schema.entries._ID, Schema.entries.title, Schema.entries.Entry};


        Cursor cursor = getContentResolver().query(Schema.Content_Uri, projection,  null,null,null);


        display(cursor);

        FloatingActionButton listiner=(FloatingActionButton) findViewById(R.id.Click);
        listiner.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,GetData.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    private void display(Cursor data){

        ListView listView=(ListView)findViewById(R.id.list);
        todoCursorAdapter adapter=new todoCursorAdapter(MainActivity.this,data);
        listView.setAdapter(adapter);

    }



}