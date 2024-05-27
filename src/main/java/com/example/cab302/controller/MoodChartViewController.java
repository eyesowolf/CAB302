package com.example.cab302.controller;

import com.example.cab302.MoodEApplication;
import com.example.cab302.dbmodelling.IUserDataDAO;
import com.example.cab302.dbmodelling.SqliteUserDataDAO;
import com.example.cab302.dbmodelling.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MoodChartViewController {
    @FXML
    private BarChart<String, Number> moodBarChart;

    IUserDataDAO userDataDAO;

    private int currentUserId;

    @FXML
    public void initialize() {
        this.currentUserId = MoodEApplication.getCurrentUserId();
        userDataDAO = new SqliteUserDataDAO(currentUserId);
        populateMoodChart();
    }

    private void populateMoodChart() {
        List<UserData> moodDataList = userDataDAO.getAllUserData();
        Map<String, Long> moodCountMap = moodDataList.stream()
                .collect(Collectors.groupingBy(UserData::getMood, Collectors.counting()));

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Long> entry : moodCountMap.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        moodBarChart.getData().add(series);
    }
    @FXML
    public void switchToMoodInput(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            MoodEApplication.showMoodInputView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void switchToSettings(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            MoodEApplication.showSettingsView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    @FXML
    public void onCloseApp(ActionEvent event){
        System.exit(0);
    }
}