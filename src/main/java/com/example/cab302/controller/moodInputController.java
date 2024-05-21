package com.example.cab302.controller;

import com.example.cab302.database.DatabaseManager;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.time.LocalDate;

public class moodInputController {
    @FXML
    private VBox root;

    @FXML
    private RadioButton happyRadioButton;
<<<<<<< Updated upstream
    @FXML
    private RadioButton angryRadioButton;
    @FXML
    private RadioButton anxiousRadioButton;
    @FXML
    private RadioButton upsetRadioButton;
    @FXML
    private RadioButton stressedRadioButton;
    @FXML
    private RadioButton inspiredRadioButton;
    @FXML
    private RadioButton confidentRadioButton;
    @FXML
    private RadioButton depressedRadioButton;
    @FXML
    private RadioButton tiredRadioButton;

    private ToggleGroup moodGroup;

=======

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

>>>>>>> Stashed changes
    public void initialize() {
        // Create linear gradient for background
        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, null,
                new Stop(0, Color.DARKBLUE),
                new Stop(1, Color.WHITE));

        // Set background with the gradient
        root.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #000080, #FFFFFF);");

<<<<<<< Updated upstream
        // Initialize database
        DatabaseManager.createNewDatabase();

        // Initialize mood group
        moodGroup = new ToggleGroup();
        happyRadioButton.setToggleGroup(moodGroup);
        angryRadioButton.setToggleGroup(moodGroup);
        anxiousRadioButton.setToggleGroup(moodGroup);
        upsetRadioButton.setToggleGroup(moodGroup);
        stressedRadioButton.setToggleGroup(moodGroup);
        inspiredRadioButton.setToggleGroup(moodGroup);
        confidentRadioButton.setToggleGroup(moodGroup);
        depressedRadioButton.setToggleGroup(moodGroup);
        tiredRadioButton.setToggleGroup(moodGroup);
    }

    @FXML
    private void handleSubmit() {
        RadioButton selectedMood = (RadioButton) moodGroup.getSelectedToggle();
        if (selectedMood != null) {
            String moodType = selectedMood.getText();
            String date = LocalDate.now().toString();
            String description = ""; // Add logic to capture description if needed

            DatabaseManager.insertMood(moodType, date, description);
        }
    }
}
=======
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
}
>>>>>>> Stashed changes
