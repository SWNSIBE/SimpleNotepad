package com.tool.simplenotepad;

import android.app.Application;
import android.content.Context;

public class NoteSkin extends Application {
    private static Context context;

    public static final String DB_Name = "note_database";
    public static final String Table_Name = "note_table";
    public static final int DB_Version = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
