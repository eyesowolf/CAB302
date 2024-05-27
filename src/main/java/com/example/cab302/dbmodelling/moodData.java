package com.example.cab302.dbmodelling;

import java.util.Date;

public class moodData {
    private int dataID;
    private String entryName;
    private Date entryDate;
    private String entryMood;
    private String entryDescription;
    private int entryUserID;

    public moodData(int dataID, String entryName, Date entryDate, String entryMood, String entryDescription, int entryUserID) {
        this.dataID = dataID;
        this.entryName = entryName;
        this.entryDate = entryDate;
        this.entryMood = entryMood;
        this.entryDescription = entryDescription;
        this.entryUserID = entryUserID;
    }

    // Getters and setters

    public int getDataID() {
        return dataID;
    }

    public void setDataID(int dataID) {
        this.dataID = dataID;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getEntryMood() {
        return entryMood;
    }

    public void setEntryMood(String entryMood) {
        this.entryMood = entryMood;
    }

    public String getEntryDescription() {
        return entryDescription;
    }

    public void setEntryDescription(String entryDescription) {
        this.entryDescription = entryDescription;
    }

    public int getEntryUserID() {
        return entryUserID;
    }

    public void setEntryUserID(int entryUserID) {
        this.entryUserID = entryUserID;
    }
}