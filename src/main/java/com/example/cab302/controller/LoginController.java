package com.example.cab302.controller;

import com.example.cab302.MoodEApplication;
import com.example.cab302.dbmodelling.SqliteUsersDAO;
import com.example.cab302.dbmodelling.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    public VBox loginContainer;
    public TextField usernameTextField;
    public PasswordField passwordPasswordField;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onLogin(ActionEvent event) {
        if (AuthenticateLogin(usernameTextField.getText(),passwordPasswordField.getText())) {
            switchToLanding(event);
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
        try {
            MoodEApplication.showCreateAccountView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void onForgotPassword(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            MoodEApplication.showForgotPasswordView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean AuthenticateLogin(String username, String password){
        SqliteUsersDAO userDAO = new SqliteUsersDAO();
        User user = userDAO.getUserByEmail(username);
        String passwordTrue = user.getPassword();
        if (Objects.equals(password, passwordTrue)){
            MoodEApplication.setCurrentUserId(user.getID());
            return true;
        }
        return false;
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
}