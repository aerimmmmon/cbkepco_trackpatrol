package com.example.cbkepco_trackpatrol;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.cbkepco_trackpatrol.SQLiteDBContract.OpticalCableTrack;


// Database version 명시
@Database(entities = {MainData.class}, version = 2, exportSchema = false)

public abstract class RoomDB extends RoomDatabase{

    // sqlite -> room migration //

    static final Migration MIGRATION_1_TO_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Room db 업데이트 시 수정사항 존재
            // 여기에 명시
        }
    };
    private static RoomDB database;

    public synchronized static RoomDB getInstance(final Context context)
    {
        if (database == null)
            synchronized (MainData.class)
        {
            database = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDB.class, OpticalCableTrack.DATABASE_NAME)
                    .addMigrations(MIGRATION_1_TO_2)
                    .allowMainThreadQueries()
                    .build();
        }
        return database;
    }
    public abstract MainDao mainDao();

}
