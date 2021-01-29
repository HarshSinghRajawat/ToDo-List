package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import com.example.todolist.Schema.entries;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GetData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);

        FloatingActionButton btn=(FloatingActionButton) findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getData();
                finish();
            }
        });
    }

    private void getData(){
        EditText str1=(EditText)findViewById(R.id.get_title);
        Editable editableField=(Editable) str1.getText();

        String title=editableField.toString().trim();

        EditText str2=(EditText)findViewById(R.id.get_entry);
        Editable editableField2=(Editable) str2.getText();


        String description=editableField2.toString().trim();

        if(title.length()<=0||description.length()<=0){
            Toast.makeText(this,"Please Give a vaild Input",Toast.LENGTH_SHORT).show();
        }else {
            DataHelper helper = new DataHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(entries.title, title);
            values.put(entries.Entry, description);

            long newRowID = db.insert(entries.Table_Name, null, values);

            if (newRowID == -1) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "New Activity Added: " + newRowID, Toast.LENGTH_SHORT).show();
            }
        }

    }
}