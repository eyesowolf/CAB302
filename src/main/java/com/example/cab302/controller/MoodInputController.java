package com.example.cab302.controller;

import com.example.cab302.SettingsController;
import com.example.cab302.ApplicationTracker;
import com.example.cab302.dbmodelling.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MoodInputController {
    @FXML
    private VBox root;

    @FXML
    private Button settingsButton;

    private User currentUser;

    public void initialize() {
        // Set background with the gradient
        root.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #000080, #FFFFFF);");
    }

    @FXML
    private void openSettings() {
        System.out.println("Settings button clicked.");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cab302/settings-view.fxml"));
            Parent root = loader.load();

            // Pass current user to settings controller
            SettingsController settingsController = loader.getController();
            settingsController.setCurrentUser(ApplicationTracker.getCurrentUser());

            Stage stage = new Stage();
            stage.setTitle("Settings");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("Failed to load settings view.");
            e.printStackTrace();
        }
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        ApplicationTracker.setCurrentUser(user);  // Set the user in ApplicationTracker as well
    }
}