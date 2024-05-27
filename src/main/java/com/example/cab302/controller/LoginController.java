package com.example.cab302.controller;

import com.example.cab302.ApplicationTracker;
import com.example.cab302.MoodEApplication;
import com.example.cab302.dbmodelling.SqliteUsersDAO;
import com.example.cab302.dbmodelling.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    public TextField usernameTextField;
    public PasswordField passwordPasswordField;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onLogin(ActionEvent event) {
        if (AuthenticateLogin(usernameTextField.getText(), passwordPasswordField.getText())) {
            User currentUser = getUserByEmail(usernameTextField.getText());
            ApplicationTracker.setCurrentUser(currentUser); // Set the current user
            switchToMoodInput(event, currentUser);
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
    protected void onForgotPassword(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            app.showForgotPasswordView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean AuthenticateLogin(String username, String password) {
        SqliteUsersDAO userDAO = new SqliteUsersDAO();
        User user = userDAO.getUserByEmail(username);
        if (user != null) {
            String passwordTrue = user.getPassword();
            return Objects.equals(password, passwordTrue);
        }
        return false;
    }

    private User getUserByEmail(String email) {
        SqliteUsersDAO userDAO = new SqliteUsersDAO();
        return userDAO.getUserByEmail(email);
    }

    public void switchToMoodInput(ActionEvent event, User currentUser) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            app.showMoodInputView(stage, currentUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//    private boolean AuthenticateLogin(String username, String password){
//        SqliteUsersDAO userDAO = new SqliteUsersDAO();
//        User user = userDAO.getUserByEmail(username);
//        String passwordTrue = user.getPassword();
//        return Objects.equals(password, passwordTrue);
//    }
