package com.example.cab302;

import com.example.cab302.controller.MoodChartViewController;
import com.example.cab302.dbmodelling.IUserDAO;
import com.example.cab302.dbmodelling.SqliteUsersDAO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

public class MoodEApplication extends Application {
    IUserDAO userDAO;

    public MoodEApplication() {
        userDAO = new SqliteUsersDAO();
    }

    @Override
    public void start(Stage stage) throws IOException {
        showLoginView(stage);
    }

    public void showLandingView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MoodEApplication.class.getResource("landing-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 800); // Set width to 1280 and height to 720
        stage.setTitle("Mood.E");
        stage.setScene(scene);
        stage.show();
    }

    public void showLoginView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MoodEApplication.class.getResource("login-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 800); // Set width to 1280 and height to 720
        stage.setTitle("Mood.E");
        stage.setScene(scene);
        stage.show();
    }

    public void showCreateAccountView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MoodEApplication.class.getResource("createAccount-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 800); // Set width to 1280 and height to 720
        stage.setTitle("Mood.E");
        stage.setScene(scene);
        stage.show();
    }

    public void showForgotPasswordView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MoodEApplication.class.getResource("forgotPassword-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 800); // Set width to 1280 and height to 720
        stage.setTitle("Mood.E");
        stage.setScene(scene);
        stage.show();
    }

    public void showMoodInputView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MoodEApplication.class.getResource("moodInput-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 800); // Set width to 1280 and height to 720
        stage.setTitle("Mood Input");
        stage.setScene(scene);
        stage.show();
    }

    public void showMoodChartView(Stage stage, int userId) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MoodEApplication.class.getResource("MoodChartView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 800); // Set width to 1280 and height to 720
        stage.setTitle("Mood Input");
        stage.setScene(scene);
        stage.show();
    }

    public void showSettingsView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MoodEApplication.class.getResource("settings-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 800); // Set width to 1280 and height to 720
        stage.setTitle("Settings");
        stage.setScene(scene);
        stage.show();
    }

    public int convertDateToEpoch(String date) {
        long epoch;
        try {
            epoch = new java.text.SimpleDateFormat("yyyy-M-d").parse(date).getTime() / 1000;
            return (int) epoch;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        launch();
    }
}