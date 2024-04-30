package com.example.cab302.dbmodelling;

public class Achievement {
        private int achievementID;
        private String achievementName;
        private String achievementDescription;

    public Achievement(int achievementID,String achievementName, String achievementDescription) {
        this.achievementID = achievementID;
        this.achievementName = achievementName;
        this.achievementDescription = achievementDescription;
    }

    public int getAchievementID() {
        return achievementID;
    }

    public void setAchievementID(int achievementID) {
        this.achievementID = achievementID;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public String getAchievementDescription() {
        return achievementDescription;
    }

    public void setAchievementDescription(String achievementDescription) {
        this.achievementDescription = achievementDescription;
    }
}
