package com.example.cab302.controller;

import com.example.cab302.dbmodelling.SqliteUsersDAO;
import com.example.cab302.dbmodelling.moodData;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MoodDisplayController {
    @FXML
    private VBox moodContainer;

    @FXML
    private Label welcomeText;

    @FXML
    private BarChart<String, Number> moodBarChart;

    private SqliteUsersDAO usersDAO;
    private int currentUserId;

    public void initialize() {
        usersDAO = new SqliteUsersDAO();
        // currentUserId should be set before this method is called
        if (currentUserId != 0) {
            populateMoodChart();
        }
    }

    public void setCurrentUserId(int userId) {
        this.currentUserId = userId;
        if (usersDAO != null) {
            populateMoodChart();
        }
    }

    private void populateMoodChart() {
        List<moodData> moodDataList = usersDAO.getMoodDataByUserId(currentUserId);

        Map<String, Long> moodCountMap = moodDataList.stream()
                .collect(Collectors.groupingBy(moodData::getEntryMood, Collectors.counting()));

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Long> entry : moodCountMap.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        moodBarChart.getData().clear();
        moodBarChart.getData().add(series);
    }
}