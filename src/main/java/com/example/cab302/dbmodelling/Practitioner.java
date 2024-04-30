package com.example.cab302.dbmodelling;

import java.sql.Blob;

public class Practitioner {
    private int practitionerID;
    private int BaseUserID;
    //private Blob prefs;

    public Practitioner(int practitionerID, int BaseUserID) {
        this.practitionerID = practitionerID;
        this.BaseUserID = BaseUserID;
    }
    public int getPractitionerID() {
        return practitionerID;
    }

    public void setPractitionerID(int practitionerID) {
        this.practitionerID = practitionerID;
    }

    public int getBaseUserID() {
        return BaseUserID;
    }

    public void setBaseUserID(int baseUserID) {
        BaseUserID = baseUserID;
    }

}
