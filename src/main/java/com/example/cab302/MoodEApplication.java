package com.example.cab302;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

public class MoodEApplication extends Application {

     private static int currentUserId;
     private static String lastUsedApp;
    static PersistentThread persistentThread = new PersistentThread();
    static AppTrackThread appTrackThread = new AppTrackThread();

    public MoodEApplication(){
        new Thread(persistentThread).start();
        new Thread(appTrackThread).start();
    }

    public static int getCurrentUserId() {
        currentUserId = persistentThread.getUserID();
        return currentUserId;
    }

    public static String getLastUsedApp(){
        lastUsedApp = appTrackThread.getLastapplication();
        return lastUsedApp;
    }

    public static void setCurrentUserId(int currentUserId) {
        MoodEApplication.currentUserId = currentUserId;
        persistentThread.setUserID(currentUserId);
    }

    @Override
    public void start(Stage stage) throws IOException {
        showLoginView(stage);
    }

    public static void showLandingView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MoodEApplication.class.getResource("landing-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 800); // Set width to 1280 and height to 720
        stage.setTitle("Mood.E");
        stage.setScene(scene);
        stage.show();
    }

    public static void showSettingsView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MoodEApplication.class.getResource("settings-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 800); // Set width to 1280 and height to 720
        stage.setTitle("Mood.E");
        stage.setScene(scene);
        stage.show();
    }

    public static void showLoginView(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(MoodEApplication.class.getResource("login-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 800); // Set width to 1280 and height to 720
        stage.setTitle("Mood.E");
        stage.setScene(scene);
        stage.show();
    }

    public static void showCreateAccountView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MoodEApplication.class.getResource("createAccount-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 800); // Set width to 1280 and height to 720
        stage.setTitle("Mood.E");
        stage.setScene(scene);
        stage.show();
    }

    public static void showForgotPasswordView(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(MoodEApplication.class.getResource("forgotPassword-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 800); // Set width to 1280 and height to 720
        stage.setTitle("Mood.E");
        stage.setScene(scene);
        stage.show();
    }

    public static void showMoodInputView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MoodEApplication.class.getResource("moodInput-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 800); // Set width to 1280 and height to 720
        stage.setTitle("Mood Input");
        stage.setScene(scene);
        stage.show();
    }

    public static void showMoodChartView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MoodEApplication.class.getResource("MoodChartView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 800); // Set width to 1280 and height to 720
        stage.setTitle("Mood Input");
        stage.setScene(scene);
        stage.show();
    }

    public static int convertDateToEpoch(String date){
        long epoch;
        try {
            epoch = new java.text.SimpleDateFormat("yyyy-M-d").parse(date).getTime() / 1000;
            return (int)epoch;
        } catch (ParseException e){
            e.printStackTrace();
        }
        return 0;
    }
    public static void main(String[] args) {
        launch();
    }
}