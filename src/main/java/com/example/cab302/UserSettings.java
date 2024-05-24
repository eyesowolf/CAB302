package com.example.cab302;

public class UserSettings {
    private boolean darkModeEnabled;
    private boolean notificationsEnabled;
    private boolean trackUsageEnabled;
    private boolean facialRecognitionEnabled;
    private boolean gameFeaturesEnabled;
    private boolean achievementFeaturesEnabled;

    // Getters and setters for dark mode
    public boolean isDarkModeEnabled() {
        return darkModeEnabled;
    }

    public void setDarkModeEnabled(boolean darkModeEnabled) {
        this.darkModeEnabled = darkModeEnabled;
    }

    // Getters and setters for notifications
    public boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setNotificationsEnabled(boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }

    // Getters and setters for track usage
    public boolean isTrackUsageEnabled() {
        return trackUsageEnabled;
    }

    public void setTrackUsageEnabled(boolean trackUsageEnabled) {
        this.trackUsageEnabled = trackUsageEnabled;
    }

    // Getters and setters for facial recognition
    public boolean isFacialRecognitionEnabled() {
        return facialRecognitionEnabled;
    }

    public void setFacialRecognitionEnabled(boolean facialRecognitionEnabled) {
        this.facialRecognitionEnabled = facialRecognitionEnabled;
    }

    // Getters and setters for game features
    public boolean areGameFeaturesEnabled() {
        return gameFeaturesEnabled;
    }

    public void setGameFeaturesEnabled(boolean gameFeaturesEnabled) {
        this.gameFeaturesEnabled = gameFeaturesEnabled;
    }

    // Getters and setters for achievement features
    public boolean areAchievementFeaturesEnabled() {
        return achievementFeaturesEnabled;
    }

    public void setAchievementFeaturesEnabled(boolean achievementFeaturesEnabled) {
        this.achievementFeaturesEnabled = achievementFeaturesEnabled;
    }
}