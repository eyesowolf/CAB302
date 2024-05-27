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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static com.example.cab302.dbmodelling.SqliteUsersDAO.*;


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
    public TextField securityQuestionAnswerTextField;
    @FXML
    private Label welcomeText;

    @FXML
    private ComboBox<String> genderComboBoxField;

    @FXML
    private ComboBox<String> securityQuestionComboBoxField;

    private ObservableList<String> genderComboBoxList = FXCollections.observableArrayList("Male","Female","Prefer not to say");

    private ObservableList<String> securityQuestionComboBoxList = FXCollections.observableArrayList("What is the name of the street you grew up in?","What is your mother's maden name?","What was your childhood nickname","What was the model of your first car?");
    private final ZoneId defaultZoneId = ZoneId.systemDefault();
    @FXML
    protected void onBack(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            MoodEApplication.showLoginView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    protected void onCreateAccount(ActionEvent event) {
        SqliteUsersDAO userDAO = new SqliteUsersDAO();
        boolean fieldCheck = AuthenticateFields();
        if (fieldCheck){
            DateFormat formatter = new SimpleDateFormat("yyyy-M-d");
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String gender = genderComboBoxField.getValue();
            String email = emailTextField.getText();
            String password = passwordPasswordField.getText();
            int dob = MoodEApplication.convertDateToEpoch(formatter.format(Date.from(dobDatePicker.getValue().atStartOfDay(defaultZoneId).toInstant())));
            String securityQuestion = securityQuestionComboBoxField.getValue();
            String securityQuestionANS = securityQuestionAnswerTextField.getText();
            String achievements = "0";
            int practitioner = 0;
            User newUser = new User(firstName, lastName, gender, email, password, dob, securityQuestion, securityQuestionANS, achievements, practitioner);
            userDAO.addUser(newUser);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            try {
                MoodEApplication.showLoginView(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    @FXML
    public void initialize(){
        genderComboBoxField.setItems(genderComboBoxList);
        securityQuestionComboBoxField.setItems(securityQuestionComboBoxList);
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

    public boolean AuthenticateFields() {
        boolean passwordCheck = AuthenticatePassword(passwordPasswordField.getText(), confirmPasswordPasswordField.getText());
        boolean emailCheck = AuthenticateEmail(emailTextField.getText());
        boolean firstNameCheck = firstNameTextField.getText().isEmpty();
        boolean lastnameCheck = lastNameTextField.getText().isEmpty();
        boolean genderCheck = genderComboBoxField.getValue() == null;
        boolean dateCheck = dobDatePicker.getValue() == null || dobDatePicker.getValue().isAfter(LocalDate.now());
        boolean secQCheck  = securityQuestionComboBoxField.getValue() == null;
        boolean secACheck = securityQuestionAnswerTextField.getText().isEmpty();
        return (passwordCheck && emailCheck) && !(firstNameCheck || lastnameCheck || genderCheck || dateCheck || secQCheck || secACheck);
    }
}