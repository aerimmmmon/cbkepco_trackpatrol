package com.example.cbkepco_trackpatrol;

import android.provider.BaseColumns;

public class SQLiteDBContract {

    private SQLiteDBContract() {}

    /* Inner class that defines the table contents */
    public static class OpticalCableTrack implements BaseColumns {

        public static final String DATABASE_NAME = "optical_cable_track.db";
        public static final String DATABASE_PATH = "database/optical_cable_track.db";
        public static final String TABLE_NAME = "navi";
        public static final String ID = "id";
        public static final String MAIN_GROUP = "mainGroup";
        public static final String SUB_GROUP = "subGroup";
        public static final String POWER_NUMBER = "powerNumber";
        public static final String POWER_NAME = "powerName";
        public static final String LAT = "lat";
        public static final String LON = "lon";
        public static final String ADDRESS = "address";

    }
}
