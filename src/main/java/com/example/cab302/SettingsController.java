package com.example.cab302;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {
    @FXML private CheckBox trackUsageCheckBox;
    @FXML private CheckBox facialRecognitionCheckBox;
    @FXML private ToggleButton themeToggleButton;
    @FXML private CheckBox gameFeaturesCheckBox;
    @FXML private CheckBox achievementFeaturesCheckBox;
    @FXML private CheckBox notificationsCheckBox;

    private UserSettings userSettings;
    private SettingsManager settingsManager = new SettingsManager();
    private int currentUserId;
    public void setCurrentUserId(int userId) {
        this.currentUserId = userId;
    }
    @FXML
    private void initialize() {
        // Load settings and update checkboxes
        userSettings = settingsManager.loadSettings();
        trackUsageCheckBox.setSelected(userSettings.isTrackUsageEnabled());
        facialRecognitionCheckBox.setSelected(userSettings.isFacialRecognitionEnabled());
        themeToggleButton.setSelected(userSettings.isDarkModeEnabled());
        themeToggleButton.setText(userSettings.isDarkModeEnabled() ? "Dark" : "Light");
        gameFeaturesCheckBox.setSelected(userSettings.areGameFeaturesEnabled());
        achievementFeaturesCheckBox.setSelected(userSettings.areAchievementFeaturesEnabled());
        notificationsCheckBox.setSelected(userSettings.isNotificationsEnabled());
    }

    @FXML
    private void onTrackUsageChanged() {
        userSettings.setTrackUsageEnabled(trackUsageCheckBox.isSelected());
        settingsManager.saveSettings(userSettings);
    }

    @FXML
    private void onFacialRecognitionChanged() {
        userSettings.setFacialRecognitionEnabled(facialRecognitionCheckBox.isSelected());
        settingsManager.saveSettings(userSettings);
    }

    @FXML
    private void onThemeToggle() {
        boolean isDarkMode = themeToggleButton.isSelected();
        userSettings.setDarkModeEnabled(isDarkMode);
        themeToggleButton.setText(isDarkMode ? "Dark" : "Light");
        settingsManager.saveSettings(userSettings);
        // Apply dark/light mode to application
    }

    @FXML
    private void onGameFeaturesChanged() {
        userSettings.setGameFeaturesEnabled(gameFeaturesCheckBox.isSelected());
        settingsManager.saveSettings(userSettings);
    }

    @FXML
    private void onAchievementFeaturesChanged() {
        userSettings.setAchievementFeaturesEnabled(achievementFeaturesCheckBox.isSelected());
        settingsManager.saveSettings(userSettings);
    }

    @FXML
    private void onNotificationsChanged() {
        userSettings.setNotificationsEnabled(notificationsCheckBox.isSelected());
        settingsManager.saveSettings(userSettings);
        // Update notification settings in application
    }

    @FXML
    private void onSaveSettings() {
        // Save selected Settings
    }

    @FXML
    private void onCancelSettings() {
        // cancel/ dont save settings

    }
    public void switchToMoodInput(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            app.showMoodInputView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void switchToLanding(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            app.showLandingView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void switchToMoodChart(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            app.showMoodChartView(stage, currentUserId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}