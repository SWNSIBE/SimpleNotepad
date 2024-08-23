package com.tool.simplenotepad.mydb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.tool.simplenotepad.NoteSkin;


@Entity(tableName = NoteSkin.Table_Name)
public class MyData {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;

    public String content ;
    public String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
