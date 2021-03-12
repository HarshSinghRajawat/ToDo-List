package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import com.example.todolist.Schema.entries;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GetData extends AppCompatActivity {

    Intent i;
    String tag="DATA";
    String title=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);

        try{
            i=getIntent();
            title=i.getSerializableExtra("title").toString();
            String body=i.getSerializableExtra("body").toString();
            editData(title,body);
        }catch (Exception e){
            Log.i(tag,""+e);
        }

        FloatingActionButton btn=(FloatingActionButton) findViewById(R.id.submit);
        if(title!=null){
            btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    try{
                        updateData();
                        Intent intent=new Intent(GetData.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }catch (Exception e){
                        Toast.makeText(GetData.this,"Err Can not update Data",Toast.LENGTH_SHORT).show();
                    }

                }
            });
            }
        else{


            btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    getData();
                    Intent intent=new Intent(GetData.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    private void  updateData() throws Exception{
        EditText str1=(EditText)findViewById(R.id.get_title);
        Editable editableField=(Editable) str1.getText();

        String title=editableField.toString().trim();

        EditText str2=(EditText)findViewById(R.id.get_entry);
        Editable editableField2=(Editable) str2.getText();


        String description=editableField2.toString().trim();

        if(title.length()<=0||description.length()<=0){
            Toast.makeText(this,"Please Give a Valid Input",Toast.LENGTH_SHORT).show();
        }else {

            ContentValues values = new ContentValues();
            values.put(entries.title, title);
            values.put(entries.Entry, description);

            //long newRowID = db.insert(entries.Table_Name, null, values);

            String[] id= {i.getSerializableExtra("Id").toString()};
            int status=getContentResolver().update(Schema.Update_Id,values, Schema.entries._ID+" =?",id);
            if (status <0) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void editData(String p,String b){
        EditText str1=(EditText)findViewById(R.id.get_title);
        str1.setText(p);
        EditText str2=(EditText)findViewById(R.id.get_entry);
        str2.setText(b);
    }

    private void getData(){
        EditText str1=(EditText)findViewById(R.id.get_title);
        Editable editableField=(Editable) str1.getText();

        String title=editableField.toString().trim();

        EditText str2=(EditText)findViewById(R.id.get_entry);
        Editable editableField2=(Editable) str2.getText();


        String description=editableField2.toString().trim();

        if(title.length()<=0||description.length()<=0){
            Toast.makeText(this,"Please Give a Valid Input",Toast.LENGTH_SHORT).show();
        }else {

            ContentValues values = new ContentValues();
            values.put(entries.title, title);
            values.put(entries.Entry, description);

            //long newRowID = db.insert(entries.Table_Name, null, values);
            Uri uri=getContentResolver().insert(Schema.Insert_Uri,values);
            long status=Long.valueOf(uri.getLastPathSegment());
            if (status == -1) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "New Activity Added", Toast.LENGTH_SHORT).show();
            }
        }

    }
}