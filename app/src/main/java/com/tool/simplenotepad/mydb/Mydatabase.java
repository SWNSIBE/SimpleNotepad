package com.tool.simplenotepad.mydb;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.exquisite.demo04.NoteSkin;

@Database(entities = {MyData.class}, version = NoteSkin.DB_Version,exportSchema = false)
public  abstract class Mydatabase extends RoomDatabase {
    public abstract Mydao mydao();
    private static Mydatabase INSTANCE;

    public static Mydatabase getInstance() {
        if (INSTANCE == null) {
            synchronized (Mydatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(NoteSkin.getContext(), Mydatabase.class, NoteSkin.DB_Name).build();
                }
            }
        }
        return INSTANCE;
    }

}
