package com.example.cab302;

import java.util.BitSet;
import java.io.*;

import com.example.cab302.controller.LoginController;
import com.example.cab302.dbmodelling.SqliteUsersDAO;
import com.example.cab302.dbmodelling.User;

public class SettingsManager {

    public UserSettings loadSettings() {
        UserSettings userSettings = new UserSettings();
        User currentUser = LoginController.currentUser;  // Get the current user

        if (currentUser == null) {
            throw new NullPointerException("Current user is not set.");
        }

        byte[] prefs = currentUser.getPrefs();

        // Initialize prefs with default values if null
        if (prefs == null) {
            prefs = new byte[]{0};
        }

        boolean[] prefValues = byteSeperate(prefs);
        userSettings.setTrackUsageEnabled(prefValues[0]);
        userSettings.setFacialRecognitionEnabled(prefValues[1]);
        userSettings.setDarkModeEnabled(prefValues[2]);
        userSettings.setGameFeaturesEnabled(prefValues[3]);
        userSettings.setAchievementFeaturesEnabled(prefValues[4]);
        userSettings.setNotificationsEnabled(prefValues[5]);
        return userSettings;
    }

    public void saveSettings(UserSettings userSettings) {
        User currentUser = LoginController.currentUser;  // Get the current user

        if (currentUser == null) {
            throw new NullPointerException("Current user is not set.");
        }

        byte[] prefs = currentUser.getPrefs();

        // Initialize prefs with default values if null
        if (prefs == null) {
            prefs = new byte[]{0};
        }


        boolean[] prefValues = byteSeperate(prefs);
        prefValues[0] = userSettings.isTrackUsageEnabled();
        prefValues[1] = userSettings.isFacialRecognitionEnabled();
        prefValues[2] = userSettings.isDarkModeEnabled();
        prefValues[3] = userSettings.areGameFeaturesEnabled();
        prefValues[4] = userSettings.areAchievementFeaturesEnabled();
        prefValues[5] = userSettings.isNotificationsEnabled();
        prefs = byteCombine(prefValues);
        currentUser.setPrefs(prefs);

        SqliteUsersDAO usersDAO = new SqliteUsersDAO();
        usersDAO.updateUser(currentUser);
    }

    private static boolean[] byteSeperate(byte[] input) {
        if (input == null) {
            return new boolean[8]; // Return default boolean array if input is null
        }
        BitSet bits = BitSet.valueOf(input);
        boolean[] output = new boolean[input.length * 8];
        for (int i = bits.nextSetBit(0); i != -1; i = bits.nextSetBit(i+1)) {
            output[i] = true;
        }
        return output;
    }

    private static byte[] byteCombine(boolean[] input) {
        byte[] output = new byte[(input.length +7) / 8];
        for (int entry = 0; entry < output.length; entry++) {
            for (int bit = 0; bit < 8; bit++) {
                if (entry * 8 + bit < input.length && input[entry * 8 + bit]) {
                    output[entry] |= (128 >> bit);
                }
            }
        }
        return output;
    }
}