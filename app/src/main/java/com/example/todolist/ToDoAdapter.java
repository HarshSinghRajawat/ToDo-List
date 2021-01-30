package com.example.todolist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ToDoAdapter extends ArrayAdapter<Data> {
    ToDoAdapter(Activity context, ArrayList<Data> data){
        super(context,0,data);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem=convertView;
        if(listItem==null){
            listItem=LayoutInflater.from(getContext()).inflate(R.layout.todo_layout,parent,false);
        }
        Data curWord=getItem(position);

        TextView title=(TextView) listItem.findViewById(R.id.title);
        TextView des=(TextView) listItem.findViewById(R.id.des);

        title.setText(curWord.getTitle());
        des.setText(curWord.getDes());
        return listItem;
    }
}
