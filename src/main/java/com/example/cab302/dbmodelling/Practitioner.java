package com.example.cab302.dbmodelling;

import java.sql.Blob;

public class Practitioner {
    private int practitionerID;
    private int BaseUserID;
    private Blob prefs;

    public Practitioner(int BaseUserID,Blob prefs) {
        this.BaseUserID = BaseUserID;
        this.prefs = prefs;
    }
    public int getID() {
        return practitionerID;
    }

    public void setID(int practitionerID) {
        this.practitionerID = practitionerID;
    }

    public int getBaseUserID() {
        return BaseUserID;
    }

    public void setBaseUserID(int baseUserID) {
        BaseUserID = baseUserID;
    }

    public Blob getPrefs() {return prefs;}
    public void setPrefs(Blob prefs){this.prefs = prefs;}
}
