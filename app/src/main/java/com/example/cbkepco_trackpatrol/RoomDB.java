package com.example.cbkepco_trackpatrol;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {MainData.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase{
    private static RoomDB database;

    private static String DATABASE_NAME = "optical_cable_track";

    public synchronized static RoomDB getInstance(Context context)
    {
        if (database == null)
        {
            database = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
    public abstract MainDao mainDao();

}