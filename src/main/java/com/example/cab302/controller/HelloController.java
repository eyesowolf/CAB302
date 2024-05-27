package com.example.cab302.controller;

import com.example.cab302.ApplicationTracker;
import com.example.cab302.MoodEApplication;
import com.example.cab302.dbmodelling.SqliteUsersDAO;
import com.example.cab302.dbmodelling.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        // Assuming ApplicationTracker is used to get the active window or current user, modify accordingly
        System.out.println(ApplicationTracker.getActiveWindow());
    }

    public void switchToMoodInput(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            // Retrieve the current user. Modify this part to get the actual current user.
            SqliteUsersDAO userDAO = new SqliteUsersDAO();
            User currentUser = userDAO.getUserByEmail("current_user_email@example.com"); // Replace with actual method to get current user

            // Pass the stage and current user to showMoodInputView
            app.showMoodInputView(stage, currentUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}