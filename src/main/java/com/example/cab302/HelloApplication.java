package com.example.cab302;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        showHelloView(stage);
    }

    private void showHelloView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 800); // Set width to 1280 and height to 720
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void showMoodInputView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("moodInput-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 800); // Set width to 1280 and height to 720
        stage.setTitle("Mood Input");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}