package com.example.cab302;


import java.util.BitSet;
import java.io.*;
import com.example.cab302.dbmodelling.SqliteUsersDAO;
import com.example.cab302.dbmodelling.User;

public class SettingsManager {

    public UserSettings loadSettings() {
        UserSettings userSettings = new UserSettings();
        SqliteUsersDAO usersDAO = new SqliteUsersDAO();
        User currentUser = usersDAO.getUserById(3);  //number(3) needs to be changed to the id of the user that is currently logged in
        byte[] prefs = currentUser.getPrefs();

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

        // below is blob data order
        //     trackUsageCheckBox; !
        //    facialRecognitionCheckBox; !
        //    themeToggleButton; !
        //    gameFeaturesCheckBox; !
        //    achievementFeaturesCheckBox; !
        //    notificationsCheckBox; !

        SqliteUsersDAO usersDAO = new SqliteUsersDAO();
        User currentUser = usersDAO.getUserById(3);  //number(3) needs to be changed to the id of the user that is currently logged in

        byte[] prefs = currentUser.getPrefs();

        boolean[] prefValues = byteSeperate(prefs);
        prefValues[0] = userSettings.isTrackUsageEnabled();
        prefValues[1] = userSettings.isFacialRecognitionEnabled();
        prefValues[2] = userSettings.isDarkModeEnabled();
        prefValues[3] = userSettings.areGameFeaturesEnabled();
        prefValues[4] = userSettings.areAchievementFeaturesEnabled();
        prefValues[5] = userSettings.isNotificationsEnabled();
        prefs = byteCombine(prefValues);
        currentUser.setPrefs(prefs);

        usersDAO.updateUser(currentUser);

    }

    private static boolean[] byteSeperate(byte[] input) {
        BitSet bits = BitSet.valueOf(input);
        boolean[] output = new boolean[input.length * 8];
        for (int i = bits.nextSetBit(0); i != -1; i = bits.nextSetBit(i+1)) {
            output[i] = true;
        }
        return output;
    }

    private static byte[] byteCombine(boolean[] input) {
        byte[] output = new byte[input.length / 8];
        for (int entry = 0; entry < output.length; entry++) {
            for (int bit = 0; bit < 8; bit++) {
                if (input[entry * 8 + bit]) {
                    output[entry] |= (128 >> bit);
                }
            }
        }

        return output;
    }
}
