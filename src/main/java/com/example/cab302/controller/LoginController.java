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
    protected void onSkipLogin(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            app.showMoodChartView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    protected void onForgotPassword(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            app.showForgotPasswordView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean AuthenticateLogin(String username, String password){
        SqliteUsersDAO userDAO = new SqliteUsersDAO();
        User user = userDAO.getUserByEmail(username);
        String passwordTrue = user.getPassword();
        return Objects.equals(password, passwordTrue);
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