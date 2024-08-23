package com.tool.simplenotepad.mydb;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface Mydao {
    @Insert
    void insertAll(List<MyData> likeDataList);

    @Query("SELECT * FROM note_table")
    List<MyData> getAllData();
    @Query("DELETE FROM note_table WHERE title = :title")
    void deletNote(String title);

}
