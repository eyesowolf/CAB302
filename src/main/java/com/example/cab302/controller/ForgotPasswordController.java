package com.example.cab302.controller;

import com.example.cab302.MoodEApplication;
import com.example.cab302.dbmodelling.SqliteUsersDAO;
import com.example.cab302.dbmodelling.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPasswordController {
    public PasswordField passwordPasswordField;
    public TextField passwordTextField;
    public PasswordField confirmPasswordPasswordField;
    public TextField confirmPasswordTextField;
    public CheckBox showPasswordCheckBox;
    public Label passwordLabel;
    public Label confirmPasswordLabel;

    enum States{
        initial,
        verification,
        reset
    }
    private States forgotPasswordState;
    public TextField emailTextField;
    public ComboBox<String> securityQuestionComboBoxField;
    public TextField answerTextField;
    public Label emailLabel;
    public Label secQLabel;
    public Label secALabel;
    private ObservableList<String> securityQuestionComboBoxList = FXCollections.observableArrayList("What is the name of the street you grew up in?","What is your mother's maden name?","What was your childhood nickname","What was the model of your first car?");
    private SqliteUsersDAO userDAO = new SqliteUsersDAO();
    private User user;

    @FXML
    public void initialize(){
        forgotPasswordState = States.initial;
        securityQuestionComboBoxField.setItems(securityQuestionComboBoxList);
    }
    @FXML
    private void onBack(ActionEvent event){
        switch (forgotPasswordState){
            case initial:
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                MoodEApplication app = new MoodEApplication();
                try {
                    app.showLoginView(stage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case verification:
                toggleVerificationScreen();
                toggleInitialScreen();
                forgotPasswordState = States.initial;
                break;
            case reset:
                toggleResetScreen();
                toggleVerificationScreen();
                forgotPasswordState = States.verification;
                break;
        }
    }

    @FXML
    protected void onShowPassword(){
        passwordTextField.textProperty().bindBidirectional(passwordPasswordField.textProperty());
        confirmPasswordTextField.textProperty().bindBidirectional(confirmPasswordPasswordField.textProperty());
        confirmPasswordPasswordField.setVisible(!confirmPasswordPasswordField.isVisible());
        passwordPasswordField.setVisible(!passwordPasswordField.isVisible());
        confirmPasswordTextField.setVisible(!confirmPasswordTextField.isVisible());
        passwordTextField.setVisible(!passwordTextField.isVisible());
    }

    @FXML
    private void onSubmit(ActionEvent event){
        switch (forgotPasswordState){
            case initial:
                initialState();
                break;
            case verification:
                verificationState();
                break;
            case reset:
                resetState();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                MoodEApplication app = new MoodEApplication();
                try {
                    app.showLoginView(stage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void initialState(){
        user = userDAO.getUserByEmail(emailTextField.getText());
        if (user != null){
            forgotPasswordState = States.verification;
            toggleInitialScreen();
            toggleVerificationScreen();
        }
    }

    private void verificationState(){
        if (Objects.equals(user.getSecQ(), securityQuestionComboBoxField.getValue())){
            if (Objects.equals(user.getSecA(), answerTextField.getText())){
                forgotPasswordState = States.reset;
                toggleVerificationScreen();
                toggleResetScreen();
            }
        }
    }

    private void resetState(){
        boolean passwordCheck = AuthenticatePassword(passwordPasswordField.getText(), confirmPasswordPasswordField.getText());
        if (passwordCheck) {
            user.setPassword(passwordPasswordField.getText());
            userDAO.updateUser(user);
        }
    }

    private void toggleInitialScreen(){
        emailLabel.setVisible(!emailLabel.isVisible());
        emailTextField.setVisible(!emailTextField.isVisible());
    }

    private void toggleVerificationScreen(){
        secQLabel.setVisible(!secQLabel.isVisible());
        securityQuestionComboBoxField.setVisible(!securityQuestionComboBoxField.isVisible());
        secALabel.setVisible(!secALabel.isVisible());
        answerTextField.setVisible(!answerTextField.isVisible());
    }

    private void toggleResetScreen(){
        passwordLabel.setVisible(!passwordLabel.isVisible());
        confirmPasswordLabel.setVisible(!confirmPasswordLabel.isVisible());
        passwordPasswordField.setVisible(!passwordPasswordField.isVisible());
        confirmPasswordPasswordField.setVisible(!confirmPasswordPasswordField.isVisible());
        showPasswordCheckBox.setVisible(!showPasswordCheckBox.isVisible());
    }

    private boolean AuthenticatePassword(String password, String confirmPassword){
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
}
