package com.example.cab302.controller;

import com.example.cab302.MoodEApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class LoginController {
    public VBox loginContainer;
    public TextField usernameTextField;
    public PasswordField passwordTextField;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onLogin(ActionEvent event) {
        if (AuthenticateLogin()) {
            switchToMoodInput(event);
        } else {
            welcomeText.setText("Login Failed, Please try again");
        }
    }
    @FXML
    protected void onExit() {
        System.exit(0);
    }
    @FXML
    protected void onCreateAccount(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            app.showCreateAccountView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void onForgotPassword() {
        welcomeText.setText("Welcome to Recovery!");
    }

    private boolean AuthenticateLogin(){
        String Username = usernameTextField.getText();
        String Password = passwordTextField.getText();
        // check db for username
        // get password for username
        // authenticate
        return Objects.equals(Username, Password);
    }

    public void switchToMoodInput(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            app.showMoodInputView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}