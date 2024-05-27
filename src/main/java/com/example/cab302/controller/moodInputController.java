package com.example.cab302.controller;

import com.example.cab302.MoodEApplication;
import com.example.cab302.dbmodelling.SqliteUsersDAO;
import com.example.cab302.dbmodelling.moodData;
import java.io.IOException;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class MoodInputController {
    @FXML
    private VBox root;

    @FXML
    private RadioButton happyRadioButton;

    @FXML
    private RadioButton anxiousRadioButton;

    @FXML
    private RadioButton angryRadioButton;

    @FXML
    private RadioButton upsetRadioButton;

    @FXML
    private RadioButton depressedRadioButton;

    @FXML
    private RadioButton stressedRadioButton;

    @FXML
    private RadioButton inspiredRadioButton;

    @FXML
    private RadioButton confidentRadioButton;

    @FXML
    private RadioButton tiredRadioButton;

    private SqliteUsersDAO usersDAO;
    private int currentUserId;

    @FXML
    public void initialize() {
        // Initialize the DAO
        usersDAO = new SqliteUsersDAO();

        // Create a ToggleGroup for the RadioButtons
        ToggleGroup moodToggleGroup = new ToggleGroup();

        // Add the RadioButtons to the ToggleGroup
        happyRadioButton.setToggleGroup(moodToggleGroup);
        anxiousRadioButton.setToggleGroup(moodToggleGroup);
        angryRadioButton.setToggleGroup(moodToggleGroup);
        upsetRadioButton.setToggleGroup(moodToggleGroup);
        depressedRadioButton.setToggleGroup(moodToggleGroup);
        stressedRadioButton.setToggleGroup(moodToggleGroup);
        inspiredRadioButton.setToggleGroup(moodToggleGroup);
        confidentRadioButton.setToggleGroup(moodToggleGroup);
        tiredRadioButton.setToggleGroup(moodToggleGroup);

        // Create linear gradient for background
        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, null,
                new Stop(0, Color.DARKBLUE),
                new Stop(1, Color.WHITE));

        // Set background with the gradient
        root.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #000080, #FFFFFF);");
    }

    public void setCurrentUserId(int userId) {
        this.currentUserId = userId;
    }

    @FXML
    private void handleSubmit() {
        // Get the selected RadioButton
        RadioButton selectedRadioButton = (RadioButton) happyRadioButton.getToggleGroup().getSelectedToggle();
        if (selectedRadioButton != null) {
            String mood = selectedRadioButton.getText();

            // Create a moodData object and save it using the DAO
            moodData newMoodData = new moodData(0, "Mood Entry", new Date(), mood, "No Description", currentUserId);

            // Save the mood data
            usersDAO.saveMoodData(newMoodData);
        }
    }

    @FXML
    public void switchToLanding(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            app.showLandingView(stage);
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
            app.showMoodChartView(stage, currentUserId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}