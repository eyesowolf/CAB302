package com.example.cab302.controller;

import com.example.cab302.MoodEApplication;
import com.example.cab302.dbmodelling.IUserDataDAO;
import com.example.cab302.dbmodelling.SqliteUserDataDAO;
import com.example.cab302.dbmodelling.UserData;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class MoodInputController {

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

    IUserDataDAO userDataDAO;
    private int currentUserId;

    @FXML
    public void initialize() {
        this.currentUserId = MoodEApplication.getCurrentUserId();
        // Initialize the DAO
        userDataDAO = new SqliteUserDataDAO(currentUserId);

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

    }

    @FXML
    private void handleSubmit() {
        // Get the selected RadioButton
        RadioButton selectedRadioButton = (RadioButton) happyRadioButton.getToggleGroup().getSelectedToggle();
        if (selectedRadioButton != null) {
            String mood = selectedRadioButton.getText();

            // Create a moodData object and save it using the DAO
            UserData newMoodData = new UserData( "Mood Entry", MoodEApplication.convertDateToEpoch(new SimpleDateFormat("yyyy-M-d").format(new Date())), mood, "No Description", currentUserId);

            // Save the mood data
            userDataDAO.updateUserData(newMoodData);
        }
    }

    @FXML
    public void switchToLanding(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            MoodEApplication.showLandingView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void switchToSettings(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            MoodEApplication.showSettingsView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void switchToMoodChart(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            MoodEApplication.showMoodChartView(stage, currentUserId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onCloseApp(ActionEvent event){
        System.exit(0);
    }
}
