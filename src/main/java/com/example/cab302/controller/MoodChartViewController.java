package com.example.cab302.controller;

import com.example.cab302.MoodEApplication;
import com.example.cab302.dbmodelling.SqliteUsersDAO;
import com.example.cab302.dbmodelling.moodData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MoodChartViewController {
    @FXML
    private BarChart<String, Number> moodBarChart;

    private SqliteUsersDAO usersDAO;

    @FXML
    public void initialize() {
        usersDAO = new SqliteUsersDAO();
        populateMoodChart();
    }

    private void populateMoodChart() {
        List<moodData> moodDataList = usersDAO.getAllMoodData();
        Map<String, Long> moodCountMap = moodDataList.stream()
                .collect(Collectors.groupingBy(moodData::getEntryMood, Collectors.counting()));

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Long> entry : moodCountMap.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        moodBarChart.getData().add(series);
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
    public void switchToSettings(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            app.showSettingsView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void switchToLanding(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MoodEApplication app = new MoodEApplication();
        try {
            app.showLandingView(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}