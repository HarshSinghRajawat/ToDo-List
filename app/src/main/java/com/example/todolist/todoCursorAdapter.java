package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class todoCursorAdapter extends CursorAdapter{
    public todoCursorAdapter(Context context, Cursor cursor){
        super(context, cursor,0);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title=(TextView) view.findViewById(R.id.title);
        TextView des=(TextView) view.findViewById(R.id.des);
        CheckBox remove=view.findViewById(R.id.checkBox);

        String priority=cursor.getString(cursor.getColumnIndexOrThrow(Schema.entries.title));
        String body=cursor.getString(cursor.getColumnIndexOrThrow(Schema.entries.Entry));
        String _id=cursor.getString(cursor.getColumnIndexOrThrow(Schema.entries._ID));

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] id= {_id};

                view.getContext().getContentResolver().delete(Schema.Del_Id,Schema.entries._ID+"=?",id);
                Toast.makeText(view.getContext(),"Well Done!!",Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();

            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent=new Intent(view.getContext(),GetData.class);
                intent.putExtra("title",priority);
                intent.putExtra("body",body);

                intent.putExtra("Id",_id);
                view.getContext().startActivity(intent);
                return true;
            }
        });





        title.setText(priority);
        des.setText(body);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.todo_layout,viewGroup, false);
    }


}
