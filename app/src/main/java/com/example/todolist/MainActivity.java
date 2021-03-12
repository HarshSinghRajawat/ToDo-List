package com.example.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        String[] projection={Schema.entries._ID, Schema.entries.title, Schema.entries.Entry};


        Cursor cursor = getContentResolver().query(Schema.Content_Uri, projection,  null,null,null);


        display(cursor);

        FloatingActionButton listiner=(FloatingActionButton) findViewById(R.id.Click);
        listiner.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,GetData.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alertDialog();


    }


    private void display(Cursor data){

        ListView listView=(ListView)findViewById(R.id.list);
        todoCursorAdapter adapter=new todoCursorAdapter(MainActivity.this,data);
        listView.setAdapter(adapter);

    }
    private void alertDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("This is a Simple To Do list Application where:-\n\n 1. You can add your Tasks by Clicking on '+'.\n\n 2.You can Edit Your Existing Tasks by Touch and Holding Tasks.\n\n3.You Check your Tasks once they are completed.\n");
        dialog.setTitle("Hint");
        dialog.setPositiveButton("YES",
                (dialog1, which) -> {

                });/*
        dialog.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"cancel is clicked",Toast.LENGTH_LONG).show();
            }
        });*/
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }



}