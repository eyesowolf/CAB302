package com.example.cab302;

import com.example.cab302.dbmodelling.User;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class SettingsController {
    @FXML
    private CheckBox trackUsageCheckBox;
    @FXML
    private CheckBox facialRecognitionCheckBox;
    @FXML
    private ToggleButton themeToggleButton;
    @FXML
    private CheckBox gameFeaturesCheckBox;
    @FXML
    private CheckBox achievementFeaturesCheckBox;
    @FXML
    private CheckBox notificationsCheckBox;

    private User currentUser;

    public void setCurrentUser(User user) {
        this.currentUser = user;
        SettingsManager.loadSettings(user, this);
    }

    @FXML
    private void onSaveSettings() {
        SettingsManager.saveSettings(currentUser, this);
        closeWindow();
    }

    @FXML
    private void onCancelSettings() {
        closeWindow();
    }

    @FXML
    private void onThemeToggle() {
        boolean isDarkMode = themeToggleButton.isSelected();
        themeToggleButton.setText(isDarkMode ? "Dark" : "Light");
    }

    private void closeWindow() {
        Stage stage = (Stage) trackUsageCheckBox.getScene().getWindow();
        stage.close();
    }

    public boolean getTrackUsage() {
        return trackUsageCheckBox.isSelected();
    }

    public void setTrackUsage(boolean value) {
        trackUsageCheckBox.setSelected(value);
    }

    public boolean getFacialRecognition() {
        return facialRecognitionCheckBox.isSelected();
    }

    public void setFacialRecognition(boolean value) {
        facialRecognitionCheckBox.setSelected(value);
    }

    public boolean getDarkMode() {
        return themeToggleButton.isSelected();
    }

    public void setDarkMode(boolean value) {
        themeToggleButton.setSelected(value);
        themeToggleButton.setText(value ? "Dark" : "Light");
    }

    public boolean getGameFeatures() {
        return gameFeaturesCheckBox.isSelected();
    }

    public void setGameFeatures(boolean value) {
        gameFeaturesCheckBox.setSelected(value);
    }

    public boolean getAchievementFeatures() {
        return achievementFeaturesCheckBox.isSelected();
    }

    public void setAchievementFeatures(boolean value) {
        achievementFeaturesCheckBox.setSelected(value);
    }

    public boolean getNotifications() {
        return notificationsCheckBox.isSelected();
    }

    public void setNotifications(boolean value) {
        notificationsCheckBox.setSelected(value);
    }
}