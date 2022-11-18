package com.example.course.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.course.Models.Notes;

import java.util.List;

@Dao
public interface MainDAO {
    @Insert(onConflict = REPLACE)
    void insert(Notes notes);

    @Query("SELECT * FROM notes ORDER BY (case when pinned then 1 else 2 end) ASC,id DESC")
    List<Notes> getAll();

    @Query("UPDATE notes SET title= :title, text=:text WHERE ID=:id")
    void update(int id, String title, String text);

    @Delete
    void delete(Notes notes);

    @Query("UPDATE notes SET pinned=:pin WHERE ID=:id")
    void pin(int id, boolean pin);
}
