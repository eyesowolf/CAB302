package com.example.cab302.controller;

import com.example.cab302.ApplicationTracker;
import com.example.cab302.HelloApplication;
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
        System.out.println(ApplicationTracker.getActiveWindow());
    }

    public void switchToMoodInput(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        HelloApplication app = new HelloApplication();
        try {
            app.showMoodInputView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}