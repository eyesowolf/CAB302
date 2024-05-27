package com.example.cab302.controller;

import com.example.cab302.MoodEApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.io.IOException;

public class moodInputController {
    @FXML
    private VBox root;

    public void initialize() {

    }
    public void switchToLanding(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            app.showMoodInputView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void switchToSettings(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            app.showSettingsView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void switchToData(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            app.showLandingView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

