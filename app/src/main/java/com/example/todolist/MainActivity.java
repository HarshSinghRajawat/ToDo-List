package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*String title,des;
        ArrayList<Data> list=new ArrayList<Data>();
        list.add(new Data(title,des));*/



        ToDoAsyncTask task=new ToDoAsyncTask();
        task.execute();


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

    protected class ToDoAsyncTask extends AsyncTask<Void,Void,ArrayList<Data>>{
        @Override
        protected void onPostExecute(ArrayList<Data> data) {
            display(data);
        }

        @Override
        protected ArrayList<Data> doInBackground(Void... voids) {
            ArrayList<Data> list=new ArrayList<Data>();
            String[] projection={Schema.entries._ID, Schema.entries.title, Schema.entries.Entry};


            DataHelper helper=new DataHelper(MainActivity.this);
            SQLiteDatabase db=helper.getReadableDatabase();
            Cursor cursor=db.query(Schema.entries.Table_Name,projection,null,null,null,null,null);

            try{
                while(cursor.moveToNext()){
                    int titleColumnIndex = cursor.getColumnIndex(Schema.entries.title);
                    int desColumnIndex = cursor.getColumnIndex(Schema.entries.Entry);
                    list.add(new Data(cursor.getString(titleColumnIndex), cursor.getString(desColumnIndex)));
                }
            }finally {
                cursor.close();
            }
            return list;
        }
    }

    private void display(ArrayList<Data> data){
        ToDoAdapter adapter=new ToDoAdapter(MainActivity.this,data);
        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
    }



}