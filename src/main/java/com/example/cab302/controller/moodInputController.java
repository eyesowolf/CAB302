package com.example.cab302.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class moodInputController {
    @FXML
    private VBox root;

    public void initialize() {
        // Create linear gradient for background
        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, null,
                new Stop(0, Color.DARKBLUE),
                new Stop(1, Color.WHITE));

        // Set background with the gradient
        root.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #000080, #FFFFFF);");
    }
}

