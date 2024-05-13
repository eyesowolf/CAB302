package com.example.cab302;

import java.util.Properties;
import java.io.*;

public class SettingsManager {
    private static final String SETTINGS_FILE = "user.settings";
    private Properties settings = new Properties();

    public UserSettings loadSettings() {
        UserSettings userSettings = new UserSettings();
        try (InputStream input = new FileInputStream(SETTINGS_FILE)) {
            settings.load(input);
            userSettings.setDarkModeEnabled(Boolean.parseBoolean(settings.getProperty("darkMode", "false")));
            userSettings.setNotificationsEnabled(Boolean.parseBoolean(settings.getProperty("notifications", "true")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return userSettings;
    }

    public void saveSettings(UserSettings userSettings) {
        settings.setProperty("darkMode", Boolean.toString(userSettings.isDarkModeEnabled()));
        settings.setProperty("notifications", Boolean.toString(userSettings.areNotificationsEnabled()));

        try (OutputStream output = new FileOutputStream(SETTINGS_FILE)) {
            settings.store(output, "User Settings");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
