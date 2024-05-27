package com.example.cab302.controller;

import com.example.cab302.ApplicationTracker;
import com.example.cab302.MoodEApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class LandingController {
    @FXML
    private Label welcomeText;

    @FXML

    public void switchToMoodInput(ActionEvent event) {
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
    public void switchToMoodChart(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            app.showMoodChartView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}