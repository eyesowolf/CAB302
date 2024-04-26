package com.example.cab302.controller;

import com.example.cab302.ApplicationTracker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class LoginController {
    public VBox loginContainer;
    public TextField usernameTextField;
    public TextField passwordTextField;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onLogin() {
        if (AuthenticateLogin()) {
            welcomeText.setText("Successful Login");
        } else {
            welcomeText.setText("Login Failed, Please try again");
        }
    }
    @FXML
    protected void onExit() {
        welcomeText.setText("Goodbye!");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }
    @FXML
    protected void onCreateAccount() {
        welcomeText.setText("Welcome Please create your account!");
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
}