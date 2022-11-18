package com.example.course.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.course.Models.Notes;

@Database(entities = Notes.class, version = 1, exportSchema = false)
public abstract class DB extends RoomDatabase {
    private static DB database;
    private static String DB_NAME="NotesApp";

    public synchronized static DB getInstance(Context context){
        if(database==null){
            database= Room.databaseBuilder(context.getApplicationContext(),
                    DB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract MainDAO mainDAO();
}
