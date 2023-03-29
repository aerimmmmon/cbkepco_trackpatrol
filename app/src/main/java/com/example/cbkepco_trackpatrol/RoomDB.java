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

    private static final String SQL_CREATE_TEMP_TABLE =
            "CREATE TABLE " + "navi_temp" + " (" +
                    OpticalCableTrack.ID + " INTEGER PRIMARY KEY," +
                    OpticalCableTrack.MAIN_GROUP + " TEXT," +
                    OpticalCableTrack.SUB_GROUP + " TEXT," +
                    OpticalCableTrack.POWER_NUMBER + " TEXT," +
                    OpticalCableTrack.POWER_NAME + " TEXT," +
                    OpticalCableTrack.LAT + " REAL," +
                    OpticalCableTrack.LON + " REAL," +
                    OpticalCableTrack.ADDRESS + " TEXT)";

    private static final String SQL_INSERT_TEMP_TABLE =
            "INSERT INTO navi_temp (" +
                    OpticalCableTrack.ID + ", " +
                    OpticalCableTrack.MAIN_GROUP + ", " +
                    OpticalCableTrack.SUB_GROUP + ", " +
                    OpticalCableTrack.POWER_NUMBER + ", " +
                    OpticalCableTrack.POWER_NAME + ", " +
                    OpticalCableTrack.LAT + ", " +
                    OpticalCableTrack.LON + ", " +
                    OpticalCableTrack.ADDRESS + ") " +
            "SELECT " +
                    OpticalCableTrack.ID + ", " +
                    OpticalCableTrack.MAIN_GROUP + ", " +
                    OpticalCableTrack.SUB_GROUP + ", " +
                    OpticalCableTrack.POWER_NUMBER + ", " +
                    OpticalCableTrack.POWER_NAME + ", " +
                    OpticalCableTrack.LAT + ", " +
                    OpticalCableTrack.LON + ", " +
                    OpticalCableTrack.ADDRESS + " FROM " + OpticalCableTrack.TABLE_NAME;

    static final Migration MIGRATION_1_TO_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase migrate_database) {
            // Room db migration시 notNull error 발생
            // 신규 테이블 생성 -> 기존 테이블에서 신규 테이블로 data copy
            // 기존 테이블 삭제 -> 신규 테이블 이름을 기존 테이블 이름으로 변경

            // Create the new table
            migrate_database.execSQL(SQL_CREATE_TEMP_TABLE);

            // Copy the data
            migrate_database.execSQL(SQL_INSERT_TEMP_TABLE);

            // Remove the old table
            migrate_database.execSQL("DROP TABLE " + OpticalCableTrack.TABLE_NAME);

            // Change the table name to the correct one
            migrate_database.execSQL("ALTER TABLE navi_temp RENAME TO " + OpticalCableTrack.TABLE_NAME);

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
