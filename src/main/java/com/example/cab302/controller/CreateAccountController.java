package com.example.cab302.controller;

import com.example.cab302.MoodEApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Objects;


public class CreateAccountController {
    /**
     * Controls the UI for the Account creation page. This class also contains functions to verify the data being entered before it is then passed onto the Database
     */
    public VBox loginContainer;
    public TextField usernameTextField;
    public TextField passwordTextField;
    public TextField emailTextField;
    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public DatePicker dobDatePicker;
    public TextField confirmPasswordTextField;
    public CheckBox showPasswordCheckBox;
    public PasswordField passwordPasswordField;
    public PasswordField confirmPasswordPasswordField;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onBack(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            app.showLoginView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    protected void onCreateAccount() {
        AuthenticatePassword(passwordPasswordField.getText(), confirmPasswordPasswordField.getText());
        AuthenticateEmail(emailTextField.getText());
    }

    @FXML
    protected void onShowPassword(){
        passwordTextField.textProperty().bindBidirectional(passwordPasswordField.textProperty());
        confirmPasswordTextField.textProperty().bindBidirectional(confirmPasswordPasswordField.textProperty());
        if (showPasswordCheckBox.isSelected()){
            confirmPasswordPasswordField.setVisible(false);
            passwordPasswordField.setVisible(false);
            confirmPasswordTextField.setVisible(true);
            passwordTextField.setVisible(true);
        } else {
            confirmPasswordTextField.setVisible(false);
            passwordTextField.setVisible(false);
            confirmPasswordPasswordField.setVisible(true);
            passwordPasswordField.setVisible(true);
        }
    }

    public boolean AuthenticatePassword(String password, String confirmPassword){
        /*
          given password and confirm Password returns if the passwords match and if the password meets the rest of the requirements
          8 characters, 1 number, 1 special character, 1 capital
          the regex pattern includes some database safety features including preventing the use of quotes and other escaping characters in the password
          regex pattern: ^((?=\S*?[A-Z])(?=\S*?[a-z])(?=\S*?[0-9])(?=\S*?(?:\W|_))[^\s"\\'{}]{8,})$
         */
        Pattern pattern = Pattern.compile("^((?=\\S*?[A-Z])(?=\\S*?[a-z])(?=\\S*?[0-9])(?=\\S*?(?:\\W|_))[^\\s\"\\\\'{}]{8,})$");
        Matcher matcher = pattern.matcher(password);
        boolean matchFound = matcher.find();
        return matchFound && Objects.equals(password, confirmPassword);
    }

    public boolean AuthenticateEmail(String email){
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}