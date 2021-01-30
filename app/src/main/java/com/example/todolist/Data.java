package com.example.todolist;

public class Data {
    private String title;
    private String des;
    Data(String str1,String str2){
        title=str1;
        des=str2;
    }
    public String getTitle(){
        return title;
    }
    public String getDes(){
        return des;
    }
}
