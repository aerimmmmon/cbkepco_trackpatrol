package com.example.cbkepco_trackpatrol;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.cbkepco_trackpatrol.SQLiteDBContract.OpticalCableTrack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SQLiteDBOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + OpticalCableTrack.TABLE_NAME + " (" +
                    OpticalCableTrack.ID + " INTEGER PRIMARY KEY," +
                    OpticalCableTrack.MAIN_GROUP + " TEXT," +
                    OpticalCableTrack.SUB_GROUP + " TEXT," +
                    OpticalCableTrack.POWER_NUMBER + " TEXT," +
                    OpticalCableTrack.POWER_NAME + " TEXT," +
                    OpticalCableTrack.LAT + " REAL," +
                    OpticalCableTrack.LON + " REAL," +
                    OpticalCableTrack.ADDRESS + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + OpticalCableTrack.TABLE_NAME;

    public SQLiteDBOpenHelper(Context context) {
        super(context, OpticalCableTrack.DATABASE_NAME, null, DATABASE_VERSION);

        File dbFile = context.getDatabasePath(OpticalCableTrack.DATABASE_PATH);

        if (!dbFile.exists()) {
            // assets 폴더에서 데이터베이스 파일을 복사하여 생성
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                inputStream = context.getAssets().open(OpticalCableTrack.DATABASE_NAME);
                outputStream = new FileOutputStream(dbFile);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        if(sqLiteDatabase == null) {
            // 테이블 생성 쿼리
            // Check if the table exists before creating it
            String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + OpticalCableTrack.TABLE_NAME + "'";
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            boolean tableExists = false;
            if (cursor != null) {
                tableExists = cursor.getCount() > 0;
                cursor.close();
            }

            if (!tableExists) {
                sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
