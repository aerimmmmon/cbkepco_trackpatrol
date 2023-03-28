package com.example.cbkepco_trackpatrol;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "navi")
public class MainData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "mainGroup")
    private String mainGroup;

    @ColumnInfo(name = "subGroup")
    private String subGroup;

    @ColumnInfo(name = "powerNumber")
    private String powerNumber;

    @ColumnInfo(name = "powerName")
    private String powerName;

    @ColumnInfo(name = "lat")
    private String lat;

    @ColumnInfo(name = "lon")
    private String lon;

    @ColumnInfo(name = "address")
    private String address;

    public String getMainGroup() {
        return mainGroup;
    }

    public void setMainGroup(String mainGroup) {
        this.mainGroup = mainGroup;
    }

    public String getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(String subGroup) {
        this.subGroup = subGroup;
    }

    public String getPowerNumber() {
        return powerNumber;
    }

    public void setPowerNumber(String powerNumber) {
        this.powerNumber = powerNumber;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
