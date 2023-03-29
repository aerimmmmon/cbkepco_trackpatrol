package com.example.cbkepco_trackpatrol;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.skt.Tmap.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // version 1 : SQLite DB
    SQLiteDBOpenHelper sqldbHelper;
    SQLiteDatabase sqliteDatabase;

    // version 2 : Room DB
    RoomDB roomDatabase;
    List<MainData> roomDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // version 1 : SQLite DB 생성
        sqldbHelper = new SQLiteDBOpenHelper(this);
        sqliteDatabase = sqldbHelper.getWritableDatabase();

        // version 2 : Room DB 생성
        roomDatabase = RoomDB.getInstance(this);
        roomDataList = roomDatabase.mainDao().getAll();

        LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.linearLayoutTmap);
        TMapView tMapView = new TMapView(this);

        tMapView.setSKTMapApiKey("XrDHTH8Y7F1A8lCf5FPF08hBNdwGq8nMaAXFFu8h");
        linearLayoutTmap.addView(tMapView);

        TMapMarkerItem markerItem1 = new TMapMarkerItem();

        TMapPoint tMapPoint1 = new TMapPoint(37.570841, 126.985302); // SKT타워

        Log.d("print", roomDataList.toString());

    }

}