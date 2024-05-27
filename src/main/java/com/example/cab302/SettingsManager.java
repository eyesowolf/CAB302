package com.example.cab302;

import com.example.cab302.dbmodelling.User;

import java.nio.ByteBuffer;
import java.util.BitSet;

public class SettingsManager {

    public static byte[] combine(boolean... values) {
        BitSet bitSet = new BitSet(values.length);
        for (int i = 0; i < values.length; i++) {
            bitSet.set(i, values[i]);
        }
        return bitSet.toByteArray();
    }

    public static boolean[] byteSeperate(byte[] array) {
        BitSet bitSet = BitSet.valueOf(array);
        boolean[] values = new boolean[bitSet.length()];
        for (int i = 0; i < values.length; i++) {
            values[i] = bitSet.get(i);
        }
        return values;
    }

    public static void loadSettings(User user, SettingsController controller) {
        System.out.println("Loading settings for user: " + user.getEmail());
        boolean[] settings = byteSeperate(user.getPrefs());
        System.out.println("Preferences array length: " + settings.length);

        if (settings.length > 0) {
            controller.setTrackUsage(settings.length > 0 && settings[0]);
            controller.setFacialRecognition(settings.length > 1 && settings[1]);
            controller.setDarkMode(settings.length > 2 && settings[2]);
            controller.setGameFeatures(settings.length > 3 && settings[3]);
            controller.setAchievementFeatures(settings.length > 4 && settings[4]);
            controller.setNotifications(settings.length > 5 && settings[5]);
        }

        System.out.println("Track Usage: " + controller.getTrackUsage());
        System.out.println("Facial Recognition: " + controller.getFacialRecognition());
        System.out.println("Dark Mode: " + controller.getDarkMode());
        System.out.println("Game Features: " + controller.getGameFeatures());
        System.out.println("Achievement Features: " + controller.getAchievementFeatures());
        System.out.println("Notifications: " + controller.getNotifications());
    }

    public static void saveSettings(User user, SettingsController controller) {
        System.out.println("Saving settings for user: " + user.getEmail());
        byte[] prefs = combine(
                controller.getTrackUsage(),
                controller.getFacialRecognition(),
                controller.getDarkMode(),
                controller.getGameFeatures(),
                controller.getAchievementFeatures(),
                controller.getNotifications()
        );

        user.setPrefs(prefs);

        System.out.println("Preferences array length: " + prefs.length);
        System.out.println("Track Usage: " + controller.getTrackUsage());
        System.out.println("Facial Recognition: " + controller.getFacialRecognition());
        System.out.println("Dark Mode: " + controller.getDarkMode());
        System.out.println("Game Features: " + controller.getGameFeatures());
        System.out.println("Achievement Features: " + controller.getAchievementFeatures());
        System.out.println("Notifications: " + controller.getNotifications());
    }
}