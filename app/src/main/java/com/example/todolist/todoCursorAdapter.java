package com.example.todolist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class todoCursorAdapter extends CursorAdapter{
    public todoCursorAdapter(Context context, Cursor cursor){
        super(context, cursor,0);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title=(TextView) view.findViewById(R.id.title);
        TextView des=(TextView) view.findViewById(R.id.des);

        String priority=cursor.getString(cursor.getColumnIndexOrThrow("DataEntry1"));
        String body=cursor.getString(cursor.getColumnIndexOrThrow("DataEntry2"));
        title.setText(priority);
        des.setText(body);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.todo_layout,viewGroup, false);
    }
}
